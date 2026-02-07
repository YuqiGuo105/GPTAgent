package com.example.MrPot.model;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mrpot_candidates")
public class MrpotCandidate {

    @Id
    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "dedupe_key", nullable = false, unique = true)
    private String dedupeKey;

    @Column(name = "status", nullable = false, length = 32)
    private String status;

    @Column(name = "type", nullable = false, length = 32)
    private String type;

    @Column(name = "session_id")
    private UUID sessionId;

    @Column(name = "model", length = 64)
    private String model;

    @Column(name = "top_k")
    private Integer topK;

    @Column(name = "min_score")
    private Double minScore;

    @Column(name = "latency_ms")
    private Integer latencyMs;

    @Column(name = "out_of_scope", nullable = false)
    private boolean outOfScope;

    @Column(name = "error", columnDefinition = "text")
    private String error;

    @Column(name = "question", nullable = false, columnDefinition = "text")
    private String question;

    @Column(name = "answer", columnDefinition = "text")
    private String answer;

    @Column(name = "canon_text", columnDefinition = "text")
    private String canonText;

    @Column(name = "scope_guard_scoped")
    private Boolean scopeGuardScoped;

    @Column(name = "scope_guard_reason", columnDefinition = "text")
    private String scopeGuardReason;

    @Column(name = "scope_guard_rewrite", columnDefinition = "text")
    private String scopeGuardRewrite;

    @Column(name = "intent", length = 32)
    private String intent;

    @Column(name = "evidence_gap_status", length = 32)
    private String evidenceGapStatus;

    @Column(name = "track_correct_on_track")
    private Boolean trackCorrectOnTrack;

    @Column(name = "track_correct_status", length = 32)
    private String trackCorrectStatus;

    @Column(name = "assumption_risk", length = 16)
    private String assumptionRisk;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tool_results", columnDefinition = "jsonb")
    private JsonNode toolResults;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id.ord ASC")
    private List<MrpotCandidateKeyStep> keySteps = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id.ord ASC")
    private List<MrpotCandidateKeyPoint> keyPoints = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MrpotCandidateEntityTerm> entityTerms = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id.ord ASC")
    private List<MrpotCandidateKeywordItem> keywordItems = new ArrayList<>();

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id.ord ASC")
    private List<MrpotCandidateKeyInfoLine> keyInfoLines = new ArrayList<>();
}
