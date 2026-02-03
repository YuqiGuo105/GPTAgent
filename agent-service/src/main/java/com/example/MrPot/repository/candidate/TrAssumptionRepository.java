package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.TrAssumptionEntity;
import com.example.MrPot.model.candidate.TrAssumptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrAssumptionRepository extends JpaRepository<TrAssumptionEntity, TrAssumptionId> {
}
