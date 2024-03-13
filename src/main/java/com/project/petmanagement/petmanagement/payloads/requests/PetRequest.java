package com.project.petmanagement.petmanagement.payloads.requests;

import java.util.Date;

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
    @NotNull(message = "pet's name is required")
    String name;
    Date dob;
    Long speciesId;
    String description;
    @NotNull(message = "You should define your pet's gender")
    Integer gender;
    String avatar;
    @NotNull(message = "Please show that your pet is neutered or not")
    Integer neutered;
}
