package com.project.petmanagement.petmanagement.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.petmanagement.petmanagement.models.enums.QualityEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutritious_food")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritiousFood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    private Species species;

    @Column(name = "image", length = 30000)
    private String image;

    @Column(name = "description", length = 5000)
    private String description;

    @Column(name = "nutrition", length = 5000)
    private String nutrition;

    @Column(name = "quality")
    @Enumerated(EnumType.STRING)
    private QualityEnum quality;

    @Column(name = "ingredient", length = 5000)
    private String ingredient;

    @ManyToOne
    @JoinColumn(name = "food_type_id", referencedColumnName = "id")
    @JsonBackReference
    private FoodType foodType;
}
