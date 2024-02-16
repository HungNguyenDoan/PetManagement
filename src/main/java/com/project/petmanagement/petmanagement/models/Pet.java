package com.project.petmanagement.petmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "pets")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname", nullable = false)
    private String fullname;
    @Column(name = "user_id")
    private String userId;
    @DateTimeFormat
    private Date dateOfBirth;
    @Column(name = "species_id",nullable = false)
    private Long speciesId;
    @Column(name = "breed")
    private String breed;
    @Column(name = "description")
    private String description;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "is_active")
    private Long isActive;
}
