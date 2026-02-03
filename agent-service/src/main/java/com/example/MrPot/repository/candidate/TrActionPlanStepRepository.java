package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.TrActionPlanStepEntity;
import com.example.MrPot.model.candidate.TrActionPlanStepId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrActionPlanStepRepository extends JpaRepository<TrActionPlanStepEntity, TrActionPlanStepId> {
}
