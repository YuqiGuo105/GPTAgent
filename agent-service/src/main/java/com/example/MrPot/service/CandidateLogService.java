package com.example.MrPot.service;

import com.example.MrPot.model.candidate.CandidateEntity;
import com.example.MrPot.model.candidate.CandidateKeyPointEntity;
import com.example.MrPot.model.candidate.CandidateKeyPointId;
import com.example.MrPot.model.candidate.CandidateKeyStepEntity;
import com.example.MrPot.model.candidate.CandidateKeyStepId;
import com.example.MrPot.model.candidate.CandidateToolResultEntity;
import com.example.MrPot.model.candidate.TrActionPlanStepEntity;
import com.example.MrPot.model.candidate.TrActionPlanStepId;
import com.example.MrPot.model.candidate.TrAnswerOutlineSectionEntity;
import com.example.MrPot.model.candidate.TrAnswerOutlineSectionId;
import com.example.MrPot.model.candidate.TrAssumptionEntity;
import com.example.MrPot.model.candidate.TrAssumptionId;
import com.example.MrPot.model.candidate.TrEntityTermEntity;
import com.example.MrPot.model.candidate.TrEntityTermId;
import com.example.MrPot.model.candidate.TrEvidenceFollowUpEntity;
import com.example.MrPot.model.candidate.TrEvidenceFollowUpId;
import com.example.MrPot.model.candidate.TrEvidenceMissingFactEntity;
import com.example.MrPot.model.candidate.TrEvidenceMissingFactId;
import com.example.MrPot.model.candidate.TrIntentSignalEntity;
import com.example.MrPot.model.candidate.TrIntentSignalId;
import com.example.MrPot.model.candidate.TrKeyInfoEntity;
import com.example.MrPot.model.candidate.TrKeyInfoId;
import com.example.MrPot.model.candidate.TrKeywordItemEntity;
import com.example.MrPot.model.candidate.TrKeywordItemId;
import com.example.MrPot.repository.candidate.CandidateKeyPointRepository;
import com.example.MrPot.repository.candidate.CandidateKeyStepRepository;
import com.example.MrPot.repository.candidate.CandidateRepository;
import com.example.MrPot.repository.candidate.CandidateToolResultRepository;
import com.example.MrPot.repository.candidate.TrActionPlanStepRepository;
import com.example.MrPot.repository.candidate.TrAnswerOutlineSectionRepository;
import com.example.MrPot.repository.candidate.TrAssumptionRepository;
import com.example.MrPot.repository.candidate.TrEntityTermRepository;
import com.example.MrPot.repository.candidate.TrEvidenceFollowUpRepository;
import com.example.MrPot.repository.candidate.TrEvidenceMissingFactRepository;
import com.example.MrPot.repository.candidate.TrIntentSignalRepository;
import com.example.MrPot.repository.candidate.TrKeyInfoRepository;
import com.example.MrPot.repository.candidate.TrKeywordItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidateLogService {

    private final CandidateRepository candidateRepository;
    private final CandidateToolResultRepository toolResultRepository;
    private final CandidateKeyStepRepository keyStepRepository;
    private final CandidateKeyPointRepository keyPointRepository;
    private final TrKeyInfoRepository keyInfoRepository;
    private final TrEntityTermRepository entityTermRepository;
    private final TrIntentSignalRepository intentSignalRepository;
    private final TrKeywordItemRepository keywordItemRepository;
    private final TrEvidenceMissingFactRepository missingFactRepository;
    private final TrEvidenceFollowUpRepository followUpRepository;
    private final TrAnswerOutlineSectionRepository outlineSectionRepository;
    private final TrActionPlanStepRepository actionPlanStepRepository;
    private final TrAssumptionRepository assumptionRepository;

    @Transactional
    public UUID saveCandidateBundle(
            CandidateEntity candidate,
            CandidateToolResultEntity toolResult,
            List<String> keyInfo,
            List<String> keySteps,
            List<String> keyPoints,
            List<String> entityTerms,
            List<String> intentSignals,
            List<String> keywordItems,
            List<String> missingFacts,
            List<String> followUps,
            List<String> outlineSections,
            List<String> actionPlanSteps,
            List<String> assumptionsJson
    ) {
        UUID candidateId = candidate.getCandidateId() == null ? UUID.randomUUID() : candidate.getCandidateId();
        candidate.setCandidateId(candidateId);

        CandidateEntity savedCandidate = candidateRepository.save(candidate);

        if (toolResult != null) {
            toolResult.setCandidate(savedCandidate);
            toolResult.setCandidateId(savedCandidate.getCandidateId());
            toolResultRepository.save(toolResult);
        }

        saveKeyInfo(savedCandidate, keyInfo);
        saveKeySteps(savedCandidate, keySteps);
        saveKeyPoints(savedCandidate, keyPoints);
        saveEntityTerms(savedCandidate, entityTerms);
        saveIntentSignals(savedCandidate, intentSignals);
        saveKeywordItems(savedCandidate, keywordItems);
        saveMissingFacts(savedCandidate, missingFacts);
        saveFollowUps(savedCandidate, followUps);
        saveOutlineSections(savedCandidate, outlineSections);
        saveActionPlanSteps(savedCandidate, actionPlanSteps);
        saveAssumptions(savedCandidate, assumptionsJson);

        return savedCandidate.getCandidateId();
    }

    private void saveKeyInfo(CandidateEntity candidate, List<String> keyInfo) {
        if (keyInfo == null || keyInfo.isEmpty()) return;
        List<TrKeyInfoEntity> rows = new ArrayList<>();
        int itemNo = 1;
        for (String item : keyInfo) {
            rows.add(TrKeyInfoEntity.builder()
                    .id(new TrKeyInfoId(candidate.getCandidateId(), itemNo++))
                    .candidate(candidate)
                    .itemText(item)
                    .build());
        }
        keyInfoRepository.saveAll(rows);
    }

    private void saveKeySteps(CandidateEntity candidate, List<String> keySteps) {
        if (keySteps == null || keySteps.isEmpty()) return;
        List<CandidateKeyStepEntity> rows = new ArrayList<>();
        int stepNo = 1;
        for (String item : keySteps) {
            rows.add(CandidateKeyStepEntity.builder()
                    .id(new CandidateKeyStepId(candidate.getCandidateId(), stepNo++))
                    .candidate(candidate)
                    .stepText(item)
                    .build());
        }
        keyStepRepository.saveAll(rows);
    }

    private void saveKeyPoints(CandidateEntity candidate, List<String> keyPoints) {
        if (keyPoints == null || keyPoints.isEmpty()) return;
        List<CandidateKeyPointEntity> rows = new ArrayList<>();
        int pointNo = 1;
        for (String item : keyPoints) {
            rows.add(CandidateKeyPointEntity.builder()
                    .id(new CandidateKeyPointId(candidate.getCandidateId(), pointNo++))
                    .candidate(candidate)
                    .pointText(item)
                    .build());
        }
        keyPointRepository.saveAll(rows);
    }

    private void saveEntityTerms(CandidateEntity candidate, List<String> entityTerms) {
        if (entityTerms == null || entityTerms.isEmpty()) return;
        List<TrEntityTermEntity> rows = new ArrayList<>();
        for (String term : entityTerms) {
            rows.add(TrEntityTermEntity.builder()
                    .id(new TrEntityTermId(candidate.getCandidateId(), term))
                    .candidate(candidate)
                    .build());
        }
        entityTermRepository.saveAll(rows);
    }

    private void saveIntentSignals(CandidateEntity candidate, List<String> intentSignals) {
        if (intentSignals == null || intentSignals.isEmpty()) return;
        List<TrIntentSignalEntity> rows = new ArrayList<>();
        for (String signal : intentSignals) {
            rows.add(TrIntentSignalEntity.builder()
                    .id(new TrIntentSignalId(candidate.getCandidateId(), signal))
                    .candidate(candidate)
                    .build());
        }
        intentSignalRepository.saveAll(rows);
    }

    private void saveKeywordItems(CandidateEntity candidate, List<String> keywordItems) {
        if (keywordItems == null || keywordItems.isEmpty()) return;
        List<TrKeywordItemEntity> rows = new ArrayList<>();
        for (String keyword : keywordItems) {
            rows.add(TrKeywordItemEntity.builder()
                    .id(new TrKeywordItemId(candidate.getCandidateId(), keyword))
                    .candidate(candidate)
                    .build());
        }
        keywordItemRepository.saveAll(rows);
    }

    private void saveMissingFacts(CandidateEntity candidate, List<String> missingFacts) {
        if (missingFacts == null || missingFacts.isEmpty()) return;
        List<TrEvidenceMissingFactEntity> rows = new ArrayList<>();
        int itemNo = 1;
        for (String fact : missingFacts) {
            rows.add(TrEvidenceMissingFactEntity.builder()
                    .id(new TrEvidenceMissingFactId(candidate.getCandidateId(), itemNo++))
                    .candidate(candidate)
                    .factText(fact)
                    .build());
        }
        missingFactRepository.saveAll(rows);
    }

    private void saveFollowUps(CandidateEntity candidate, List<String> followUps) {
        if (followUps == null || followUps.isEmpty()) return;
        List<TrEvidenceFollowUpEntity> rows = new ArrayList<>();
        int itemNo = 1;
        for (String followUp : followUps) {
            rows.add(TrEvidenceFollowUpEntity.builder()
                    .id(new TrEvidenceFollowUpId(candidate.getCandidateId(), itemNo++))
                    .candidate(candidate)
                    .followUpText(followUp)
                    .build());
        }
        followUpRepository.saveAll(rows);
    }

    private void saveOutlineSections(CandidateEntity candidate, List<String> outlineSections) {
        if (outlineSections == null || outlineSections.isEmpty()) return;
        List<TrAnswerOutlineSectionEntity> rows = new ArrayList<>();
        int sectionNo = 1;
        for (String section : outlineSections) {
            rows.add(TrAnswerOutlineSectionEntity.builder()
                    .id(new TrAnswerOutlineSectionId(candidate.getCandidateId(), sectionNo++))
                    .candidate(candidate)
                    .sectionText(section)
                    .build());
        }
        outlineSectionRepository.saveAll(rows);
    }

    private void saveActionPlanSteps(CandidateEntity candidate, List<String> actionPlanSteps) {
        if (actionPlanSteps == null || actionPlanSteps.isEmpty()) return;
        List<TrActionPlanStepEntity> rows = new ArrayList<>();
        int stepNo = 1;
        for (String step : actionPlanSteps) {
            rows.add(TrActionPlanStepEntity.builder()
                    .id(new TrActionPlanStepId(candidate.getCandidateId(), stepNo++))
                    .candidate(candidate)
                    .stepText(step)
                    .build());
        }
        actionPlanStepRepository.saveAll(rows);
    }

    private void saveAssumptions(CandidateEntity candidate, List<String> assumptionsJson) {
        if (assumptionsJson == null || assumptionsJson.isEmpty()) return;
        List<TrAssumptionEntity> rows = new ArrayList<>();
        int assumptionNo = 1;
        for (String assumptionJson : assumptionsJson) {
            rows.add(TrAssumptionEntity.builder()
                    .id(new TrAssumptionId(candidate.getCandidateId(), assumptionNo++))
                    .candidate(candidate)
                    .assumptionJson(assumptionJson)
                    .build());
        }
        assumptionRepository.saveAll(rows);
    }
}
