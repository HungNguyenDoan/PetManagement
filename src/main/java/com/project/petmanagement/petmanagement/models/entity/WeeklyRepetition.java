package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Table(name = "weekly_repetition")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyRepetition extends Repetition {
    @Column(name = "day", nullable = false)
    private DayOfWeek day;

    @Column(name = "time", nullable = false)
    private Time time;
}
