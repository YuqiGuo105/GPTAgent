package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.CandidateKeyPointEntity;
import com.example.MrPot.model.candidate.CandidateKeyPointId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateKeyPointRepository extends JpaRepository<CandidateKeyPointEntity, CandidateKeyPointId> {
}
