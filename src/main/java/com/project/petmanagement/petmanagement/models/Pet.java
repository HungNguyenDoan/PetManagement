package com.project.petmanagement.petmanagement.models;

import jakarta.persistence.*;
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
    private Integer isActive;

    @Column(name = "gender")
    private Integer gender; // 1 : đực, 0 : cái

    @Column(name = "neutered")
    private Integer neutered; // 1: true, 0: false
    @Column(name = "avatar", columnDefinition="TEXT")
    private String avatar;
}
