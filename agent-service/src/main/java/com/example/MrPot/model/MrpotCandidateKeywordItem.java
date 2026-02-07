package com.example.MrPot.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mrpot_candidate_keywords_items")
public class MrpotCandidateKeywordItem {

    @EmbeddedId
    private CandidateOrdId id;

    @MapsId("candidateId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "candidate_id", nullable = false)
    private MrpotCandidate candidate;

    @Column(name = "item_text", nullable = false, columnDefinition = "text")
    private String itemText;

    @Column(name = "source", columnDefinition = "text")
    private String source;
}
