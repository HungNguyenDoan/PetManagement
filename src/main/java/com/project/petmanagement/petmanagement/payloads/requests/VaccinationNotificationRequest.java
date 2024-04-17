package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

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

    private Date date;

    private Time time;

    @JsonProperty("alarm_before")
    private Time alarmBefore;
}
