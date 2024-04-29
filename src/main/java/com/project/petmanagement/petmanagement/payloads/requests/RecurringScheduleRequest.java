package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.petmanagement.petmanagement.models.enums.FrequencyEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecurringScheduleRequest {
    private Long id;

    private String name;

    @NotNull(message = "You have to set the frequency")
    private FrequencyEnum frequency;

    @NotNull(message = "You have to set the value of frequency")
    private Integer value;

    @JsonProperty("days_of_month")
    private List<Integer> daysOfMonth;

    @JsonProperty("days_of_week")
    private List<DayOfWeek> daysOfWeek;

    private Date date;

    private String time;

    @JsonProperty("from_date")
    private Date fromDate;

    @JsonProperty("to_date")
    private Date toDate;
}
