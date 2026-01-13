package com.example.MrPot.service;

import com.example.MrPot.model.CandidateIngestRequest;
import com.example.MrPot.model.CandidateIngestResponse;
import com.example.MrPot.repository.TempCandidateRepository;
import com.example.MrPot.service.KeyInfoExtractor.KeyInfo;
import com.example.MrPot.util.Sha256;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateIngestionService {

    private final TempCandidateRepository tempRepo;
    private final ElasticCandidateIndexer esIndexer;
    private final ObjectMapper objectMapper;

    @Value("${app.candidate.store-to-es:true}")
    private boolean storeToEs;

    public CandidateIngestResponse ingest(CandidateIngestRequest req) {
        if (req == null) {
            return new CandidateIngestResponse(false, null, null);
        }

        String dedupeKey = Sha256.hex(
                safe(req.sessionId()) + "|" +
                        safe(req.question()) + "|" +
                        safe(req.model()) + "|" +
                        safe(req.answer()) + "|" +
                        safe(req.error())
        );

        KeyInfo ki = KeyInfoExtractor.extract(req.answer());

        String evidenceJson = toJson(req.evidence());
        String metaJson = toJson(req.meta() == null ? Map.of() : req.meta());
        String keyStepsJson = toJson(ki.steps());
        String keyPointsJson = toJson(ki.points());

        Optional<UUID> insertedId = tempRepo.insertIgnoreConflict(new TempCandidateRepository.TempRow(
                dedupeKey,
                "NEW",
                "QA",
                req.sessionId(),
                req.model(),
                req.topK(),
                req.minScore(),
                req.latencyMs(),
                req.outOfScope(),
                req.error(),
                req.question(),
                req.answer(),
                keyStepsJson,
                keyPointsJson,
                evidenceJson,
                metaJson
        ));

        boolean inserted = insertedId.isPresent();
        UUID candidateId = insertedId.orElse(null);

        if (storeToEs) {
            Map<String, Object> doc = new LinkedHashMap<>();
            doc.put("@timestamp", Instant.now().toString());
            doc.put("candidate_id", candidateId == null ? null : candidateId.toString());
            doc.put("dedupe_key", dedupeKey);
            doc.put("status", "NEW");
            doc.put("type", "QA");

            doc.put("session_id", req.sessionId());
            doc.put("model", req.model());
            doc.put("top_k", req.topK());
            doc.put("min_score", req.minScore());
            doc.put("latency_ms", req.latencyMs());
            doc.put("out_of_scope", req.outOfScope());
            doc.put("error", req.error());

            doc.put("question", req.question());
            doc.put("answer", req.answer());
            doc.put("canon_text", "Q: " + safe(req.question()) + "\nA: " + safe(req.answer()));

            doc.put("key_steps", ki.steps());
            doc.put("key_points", ki.points());

            esIndexer.upsertAsync(dedupeKey, doc);
        }

        return new CandidateIngestResponse(inserted, candidateId, dedupeKey);
    }

    private String toJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o == null ? Map.of() : o);
        } catch (Exception e) {
            return "{}";
        }
    }

    private static String safe(String s) {
        return s == null ? "" : s;
    }
}
