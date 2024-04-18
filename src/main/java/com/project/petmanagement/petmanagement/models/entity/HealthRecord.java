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

    @Column(name = "checkup_date")
    private Date checkUpDate;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "exercise_level")
    private Integer exerciseLevel;

    @Column(name = "last_visit")
    private Date lastVisit;

    @Column(name = "symptoms", length = 5000)
    private String symptoms;

    @Column(name = "diagnosis", length = 5000)
    private String diagnosis;

    @Column(name = "treatments", length = 5000)
    private String treatments;

    @Column(name = "note", length = 5000)
    private String note;

    @ManyToOne
    @JoinColumn(name = "pet_id", referencedColumnName = "id")
    private Pet pet;
}
