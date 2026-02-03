package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.TrEntityTermEntity;
import com.example.MrPot.model.candidate.TrEntityTermId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrEntityTermRepository extends JpaRepository<TrEntityTermEntity, TrEntityTermId> {
}
