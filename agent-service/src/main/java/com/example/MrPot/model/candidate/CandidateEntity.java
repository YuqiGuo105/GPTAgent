package com.example.MrPot.model.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "candidate", schema = "public")
public class CandidateEntity {

    @Id
    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id")
    private ChatSessionEntity session;

    @Column(name = "es_index", nullable = false)
    private String esIndex;

    @Column(name = "es_id", nullable = false)
    private String esId;

    @Column(name = "es_version", nullable = false)
    private Integer esVersion;

    @Column(name = "ingested_at", nullable = false)
    private Instant ingestedAt;

    @Column(name = "dedupe_key", nullable = false, unique = true)
    private String dedupeKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private DimStatusEntity status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id", nullable = false)
    private DimCandidateTypeEntity type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id")
    private DimModelEntity model;

    @Column(name = "top_k")
    private Integer topK;

    @Column(name = "min_score", precision = 10, scale = 4)
    private BigDecimal minScore;

    @Column(name = "latency_ms")
    private Integer latencyMs;

    @Column(name = "out_of_scope", nullable = false)
    private boolean outOfScope;

    @Column(name = "error_json", columnDefinition = "jsonb")
    private String errorJson;

    @Lob
    @Column(name = "question")
    private String question;

    @Lob
    @Column(name = "answer")
    private String answer;

    @Lob
    @Column(name = "canon_text")
    private String canonText;

    @Column(name = "source_json", nullable = false, columnDefinition = "jsonb")
    private String sourceJson;

    @Column(name = "fields_json", columnDefinition = "jsonb")
    private String fieldsJson;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
