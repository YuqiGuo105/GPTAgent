package com.example.MrPot.model.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TrAnswerOutlineSectionId implements Serializable {

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @Column(name = "section_no", nullable = false)
    private Integer sectionNo;
}
