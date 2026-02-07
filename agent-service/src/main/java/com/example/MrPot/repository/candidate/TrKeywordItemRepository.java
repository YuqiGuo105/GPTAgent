package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.TrKeywordItemEntity;
import com.example.MrPot.model.candidate.TrKeywordItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrKeywordItemRepository extends JpaRepository<TrKeywordItemEntity, TrKeywordItemId> {
}
