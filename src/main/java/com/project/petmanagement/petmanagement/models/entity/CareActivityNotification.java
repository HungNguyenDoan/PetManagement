package com.project.petmanagement.petmanagement.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "care_activity_notifications")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CareActivityNotification extends Notification{
    @OneToMany(mappedBy = "careActivityNotification", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<CareActivityInfo> careActivityInfoList;

    @OneToOne(mappedBy = "careActivityNotification")
    private RecurringSchedule schedule;
}
