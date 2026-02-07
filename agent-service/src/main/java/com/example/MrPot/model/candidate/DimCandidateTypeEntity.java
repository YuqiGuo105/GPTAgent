package com.example.MrPot.model.candidate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "dim_candidate_type", schema = "public")
public class DimCandidateTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Short typeId;

    @Column(name = "type_code", nullable = false, unique = true)
    private String typeCode;

    @Column(name = "type_name", nullable = false)
    private String typeName;
}
