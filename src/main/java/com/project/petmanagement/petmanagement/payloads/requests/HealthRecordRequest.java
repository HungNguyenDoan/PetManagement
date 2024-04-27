package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Data
@NoArgsConstructor
public class HealthRecordRequest {
    @JsonProperty("check_update")
    @NotNull(message = "Check update is required.")
    private Date checkUpDate;
    @NotNull(message = "Weight is required")
    private Double weight;
    @JsonProperty("exercise_level")
    @NotNull(message = "Exercise_level is required")
    private Integer exerciseLevel;
    private String symptoms;
    private String diagnosis;
    private String note;
    @JsonProperty("pet_id")
    @NotNull(message = "Pet is required")
    private Long petId;
}
