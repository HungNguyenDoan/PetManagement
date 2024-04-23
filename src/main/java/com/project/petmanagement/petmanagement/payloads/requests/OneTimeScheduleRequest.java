package com.project.petmanagement.petmanagement.payloads.requests;

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
public class OneTimeScheduleRequest {
    private Long id;

    private Date date;

    private Time time;

    private Boolean status;
}
