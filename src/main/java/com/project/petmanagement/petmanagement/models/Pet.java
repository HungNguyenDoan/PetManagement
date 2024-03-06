package com.project.petmanagement.petmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "species_id")
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
    private int isActive;

    @Column(name = "gender")
    private int gender; // 1 : đực, 2 : cái

    @Column(name = "neutered")
    private boolean neutered;
    @Column(name = "avatar", columnDefinition="TEXT")
    private String avatar;

}
