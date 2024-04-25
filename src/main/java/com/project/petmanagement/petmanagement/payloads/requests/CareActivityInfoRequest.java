package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareActivityInfoRequest {
    private Long id;

    @JsonProperty("care_activity_id")
    private Long careActivityId;

    private String note;
}
