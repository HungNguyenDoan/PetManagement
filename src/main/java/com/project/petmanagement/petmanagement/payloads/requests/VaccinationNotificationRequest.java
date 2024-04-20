package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationNotificationRequest {
    @JsonProperty("pet_id")
    private Long petId;

    private String title;

    @JsonProperty("vaccine_id")
    private Long vaccineId;

    private String note;

    @JsonProperty("schedules")
    @NotNull(message = "You have to set schedule for vaccination")
    private List<OneTimeScheduleRequest> oneTimeScheduleRequestList;
}
