package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Time;
import java.time.DayOfWeek;

@Entity
@Table(name = "weekly_repetition")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeeklyRepetition extends Repetition {
    @Column(name = "day")
    private DayOfWeek day;

    @Column(name = "time")
    private Time time;
}
