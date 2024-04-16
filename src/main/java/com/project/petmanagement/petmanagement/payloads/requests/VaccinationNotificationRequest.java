package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationNotificationRequest {
    @JsonProperty("species_id")
    Long speciesId;

    @JsonProperty("food_type_id")
    Long foodTypeId;

    @JsonProperty("keywords")
    String keywords;
}
