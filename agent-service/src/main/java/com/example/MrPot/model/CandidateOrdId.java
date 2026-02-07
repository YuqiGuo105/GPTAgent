package com.example.MrPot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class CandidateOrdId implements Serializable {

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "ord", nullable = false)
    private short ord;
}
