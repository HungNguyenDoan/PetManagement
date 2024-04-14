package com.project.petmanagement.petmanagement.models.entity;

import com.project.petmanagement.petmanagement.models.enums.FrequencyEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recurring_schedules")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecurringSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "frequency", nullable = false)
    private FrequencyEnum frequency;

    @Column(name = "value", nullable = false)
    private Integer value;

    @OneToOne
    @JoinColumn(name = "care_activity_notification_id", referencedColumnName = "id")
    private CareActivityNotification careActivityNotification;
}
