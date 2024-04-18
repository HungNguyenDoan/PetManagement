package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "no_repetition")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoRepetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "alarm_before")
    private Time alarmBefore;

    @OneToOne
    @JoinColumn(name = "recurring_schedule_id", referencedColumnName = "id")
    private RecurringSchedule recurringSchedule;
}
