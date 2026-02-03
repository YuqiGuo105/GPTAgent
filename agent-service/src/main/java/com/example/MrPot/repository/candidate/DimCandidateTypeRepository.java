package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.DimCandidateTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimCandidateTypeRepository extends JpaRepository<DimCandidateTypeEntity, Short> {
}
