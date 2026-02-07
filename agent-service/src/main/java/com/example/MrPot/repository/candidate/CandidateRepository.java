package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.CandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {
}
