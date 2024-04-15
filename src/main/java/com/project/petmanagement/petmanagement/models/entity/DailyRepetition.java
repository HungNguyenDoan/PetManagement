package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Time;

@Entity
@Table(name = "daily_repetition")
@AllArgsConstructor
@NoArgsConstructor
public class DailyRepetition extends Repetition{
    @Column(name = "time", nullable = false)
    private Time time;
}
