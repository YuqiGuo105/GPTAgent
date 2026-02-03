package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.CandidateKeyStepEntity;
import com.example.MrPot.model.candidate.CandidateKeyStepId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateKeyStepRepository extends JpaRepository<CandidateKeyStepEntity, CandidateKeyStepId> {
}
