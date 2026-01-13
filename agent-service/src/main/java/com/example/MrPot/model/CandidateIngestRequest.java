package com.example.MrPot.model;

import java.util.Map;

public record CandidateIngestRequest(
        String sessionId,
        String model,
        Integer topK,
        Double minScore,
        Integer latencyMs,
        Boolean outOfScope,
        String error,
        String question,
        String answer,
        Object evidence,
        Map<String, Object> meta
) {}
