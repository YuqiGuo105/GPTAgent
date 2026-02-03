package com.example.MrPot.model.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "candidate_tool_result", schema = "public")
public class CandidateToolResultEntity {

    @Id
    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;

    @Column(name = "scope_guard_scoped")
    private Boolean scopeGuardScoped;

    @Column(name = "scope_guard_reason")
    private String scopeGuardReason;

    @Column(name = "scope_guard_rewrite_hint")
    private String scopeGuardRewriteHint;

    @Column(name = "intent_intent")
    private String intentIntent;

    @Column(name = "keywords_source")
    private String keywordsSource;

    @Column(name = "evidence_gap_status")
    private String evidenceGapStatus;

    @Column(name = "answer_outline_style")
    private String answerOutlineStyle;

    @Column(name = "assumption_check_risk")
    private String assumptionCheckRisk;

    @Column(name = "action_plan_style")
    private String actionPlanStyle;

    @Column(name = "track_correct_on_track")
    private Boolean trackCorrectOnTrack;

    @Column(name = "track_correct_status")
    private String trackCorrectStatus;

    @Column(name = "track_correct_hint")
    private String trackCorrectHint;

    @Lob
    @Column(name = "tool_results_json", nullable = false, columnDefinition = "jsonb")
    private String toolResultsJson;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;
}
