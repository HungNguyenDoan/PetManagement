package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareActivityNotificationRequest {
    @JsonProperty("pet_id")
    @NotNull(message = "Pet can not be null.")
    private Long petId;

    private String title;

    @JsonProperty("care_activity_info_list")
    @NotEmpty(message = "Care activity information is required.")
    private List<CareActivityInfoRequest> careActivityInfoRequestList;

    private String note;

    @JsonProperty("schedule")
    @NotNull(message = "Schedule is required.")
    private RecurringScheduleRequest recurringScheduleRequest;

    @JsonProperty("notification_status")
    private Boolean notificationStatus;
}
