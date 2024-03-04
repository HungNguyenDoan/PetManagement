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
    @NotNull
    private String fullname;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @NotNull
    @Column(name = "species_id")
    private Long speciesId;
    @NotNull
    @Column(name = "breed")
    private String breed;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "created_at")
    private Date createdAt;

    @NotNull
    @Column(name = "updated_at")
    private Date updatedAt;

    @NotNull
    @Column(name = "is_active")
    private int isActive;

    @NotNull
    @Column(name = "gender")
    private int gender; // 1 : đực, 2 : cái

    @NotNull
    @Column(name = "neutered")
    private int neutered;
    //true là đã thiến
    @Column(name = "avatar", columnDefinition="TEXT")
    private String avatar;

}
