package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "health_records")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "checkup_date", nullable = false)
    private Date checkUpDate;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "exercise_level")
    private Integer exerciseLevel;

    @Column(name = "last_visit")
    private Date lastVisit;

    @Column(name = "symptoms")
    private String symptoms;

    @Column(name = "diagnosis")
    private String diagnosis;

    @Column(name = "treatment")
    private String treatment;

    @Column(name = "note")
    private String note;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;
}
