package com.example.MrPot.repository.candidate;

import com.example.MrPot.model.candidate.ChatSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatSessionRepository extends JpaRepository<ChatSessionEntity, UUID> {
}
