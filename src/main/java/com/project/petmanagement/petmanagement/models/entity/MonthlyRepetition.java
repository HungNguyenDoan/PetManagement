package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Table(name = "monthly_repetition")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyRepetition extends Repetition {
    @Column(name = "date", nullable = false)
    private Integer date;

    @Column(name = "time", nullable = false)
    private Time time;
}
