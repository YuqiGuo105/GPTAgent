package com.example.MrPot.model;

import java.util.UUID;

public record CandidateIngestResponse(
        boolean inserted,
        UUID candidateId,
        String dedupeKey
) {}
