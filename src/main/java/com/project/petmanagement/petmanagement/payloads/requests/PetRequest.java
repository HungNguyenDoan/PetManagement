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
    Long id;
    @NotNull(message = "pet's name is required")
    String name;
    Date dob;
    Long speciesId;
    String description;
    Integer gender;
    String avatar;
    String breed;
}
