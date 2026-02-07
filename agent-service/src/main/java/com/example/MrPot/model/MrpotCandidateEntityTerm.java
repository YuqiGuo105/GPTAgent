package com.example.MrPot.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mrpot_candidate_entity_terms")
public class MrpotCandidateEntityTerm {

    @EmbeddedId
    private CandidateTermId id;

    @MapsId("candidateId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id", nullable = false)
    private MrpotCandidate candidate;
}
