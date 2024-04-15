package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Time;

@Entity
@Table(name = "monthly_repetition")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyRepetition extends Repetition {
    @Column(name = "date", nullable = false)
    private Integer date;

    @Column(name = "time", nullable = false)
    private Time time;
}
