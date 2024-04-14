package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@MappedSuperclass
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Repetition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_date", nullable = false)
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "alarm_before")
    private Time alarmBefore;

    @ManyToOne
    @JoinColumn(name = "recurring_schedule_id", referencedColumnName = "id")
    private RecurringSchedule recurringSchedule;
}
