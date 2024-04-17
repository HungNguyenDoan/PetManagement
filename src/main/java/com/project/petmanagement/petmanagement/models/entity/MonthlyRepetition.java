package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Time;

@Entity
@Table(name = "monthly_repetition")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyRepetition extends Repetition {
    @Column(name = "date")
    private Integer date;

    @Column(name = "time")
    private Time time;
}
