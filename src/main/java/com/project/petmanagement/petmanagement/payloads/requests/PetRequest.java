package com.project.petmanagement.petmanagement.payloads.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetRequest {
    @JsonProperty("full_name")
    @NotNull(message = "Pet's name is required.")
    String fullName;

    @JsonProperty("breed_id")
    @NotNull(message = "Pet's breed is required.")
    Long breedId;

    @NotNull(message = "You should define your pet's gender")
    Integer gender;

    @JsonProperty("date_of_birth")
    @NotNull(message = "Pet's date of birth is required.")
    Date dateOfBirth;

    String description;

    String image;

    @JsonProperty("is_neutered")
    @NotNull(message = "Please show that your pet is neutered or not")
    Boolean isNeutered;
}
