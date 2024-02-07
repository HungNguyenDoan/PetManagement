package com.project.petmanagement.petmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetDTO {
    private Long id;
    private Long userId;
    private String fullName;
    private Date dateOfBirth;
    private Long speciesId;
    private String breed;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private Long isActive;
    private Date dateTo;
    private Date dateFrom;
}
