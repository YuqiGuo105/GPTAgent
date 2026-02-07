package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.CandidateToolResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateToolResultRepository extends JpaRepository<CandidateToolResultEntity, UUID> {
}
