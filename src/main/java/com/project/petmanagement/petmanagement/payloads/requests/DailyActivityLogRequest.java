package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyActivityLogRequest {
    private Long id;

    private String title;

    private Date date;

    private Time time;

    private List<String> images;

    private String note;

    @JsonProperty("pet_id")
    private Long petId;

    @JsonProperty("daily_activity_id")
    private Long dailyActivityId;
}
