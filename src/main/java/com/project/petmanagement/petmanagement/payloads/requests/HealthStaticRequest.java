package com.project.petmanagement.petmanagement.payloads.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthStaticRequest {
    @JsonProperty("pet_id")
    Long petId;
    
    @JsonProperty("start_date")
    Date startDate;

    @JsonProperty("end_date")
    Date endDate;
}
