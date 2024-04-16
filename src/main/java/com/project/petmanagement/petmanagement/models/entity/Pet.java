package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "pets")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
public class Pet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "breed_id", referencedColumnName = "id")
    private Breed breed;

    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "description")
    private String description;

    @Column(name = "image", length = 30000)
    private String image;

    @Column(name = "is_neutered", nullable = false)
    private Boolean isNeutered;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
