package com.project.petmanagement.petmanagement.payloads.requests;

import com.project.petmanagement.petmanagement.models.enums.FrequencyEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.time.DayOfWeek;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecurringScheduleRequest {
    private Long id;

    @NotNull(message = "You have to set the frequency")
    private FrequencyEnum frequency;

    @NotNull(message = "You have to set the value of frequency")
    private Integer value;

    private Date date;

    private DayOfWeek day;

    private Time time;
}
