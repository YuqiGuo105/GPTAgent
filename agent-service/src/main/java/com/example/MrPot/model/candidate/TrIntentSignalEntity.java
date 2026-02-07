package com.example.MrPot.model.candidate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tr_intent_signal", schema = "public")
public class TrIntentSignalEntity {

    @EmbeddedId
    private TrIntentSignalId id;

    @MapsId("candidateId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private CandidateEntity candidate;
}
