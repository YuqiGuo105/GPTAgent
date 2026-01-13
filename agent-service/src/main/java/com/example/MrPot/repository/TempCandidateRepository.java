package com.example.MrPot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TempCandidateRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public Optional<UUID> insertIgnoreConflict(TempRow row) {
        String sql = """
            INSERT INTO temp.knowledge_candidate
              (dedupe_key, status, type, session_id, model, top_k, min_score, latency_ms, out_of_scope, error,
               question, answer, key_steps, key_points, evidence, meta)
            VALUES
              (:dedupe_key, :status, :type, :session_id, :model, :top_k, :min_score, :latency_ms, :out_of_scope, :error,
               :question, :answer, CAST(:key_steps AS jsonb), CAST(:key_points AS jsonb), CAST(:evidence AS jsonb), CAST(:meta AS jsonb))
            ON CONFLICT (dedupe_key) DO NOTHING
            RETURNING id
            """;

        Map<String, Object> p = new HashMap<>();
        p.put("dedupe_key", row.dedupeKey());
        p.put("status", row.status());
        p.put("type", row.type());
        p.put("session_id", row.sessionId());
        p.put("model", row.model());
        p.put("top_k", row.topK());
        p.put("min_score", row.minScore());
        p.put("latency_ms", row.latencyMs());
        p.put("out_of_scope", row.outOfScope());
        p.put("error", row.error());

        p.put("question", row.question());
        p.put("answer", row.answer());

        p.put("key_steps", row.keyStepsJson());
        p.put("key_points", row.keyPointsJson());
        p.put("evidence", row.evidenceJson());
        p.put("meta", row.metaJson());

        UUID id = jdbc.query(sql, p, rs -> rs.next() ? (UUID) rs.getObject(1) : null);
        return Optional.ofNullable(id);
    }

    public record TempRow(
            String dedupeKey,
            String status,
            String type,
            String sessionId,
            String model,
            Integer topK,
            Double minScore,
            Integer latencyMs,
            Boolean outOfScope,
            String error,
            String question,
            String answer,
            String keyStepsJson,
            String keyPointsJson,
            String evidenceJson,
            String metaJson
    ) {}
}
