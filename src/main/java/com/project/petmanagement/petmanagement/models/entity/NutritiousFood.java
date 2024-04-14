package com.project.petmanagement.petmanagement.models.entity;

import com.project.petmanagement.petmanagement.models.enums.QualityEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "species_id", referencedColumnName = "id")
    private Species species;

    @Column(name = "image", length = 30000, nullable = false)
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "nutrition", nullable = false)
    private String nutrition;

    @Column(name = "quality", nullable = false)
    private QualityEnum quality;

    @Column(name = "ingredient", nullable = false)
    private String ingredient;

    // NutritiousFood có quan hệ n - n với FoodType
    @ManyToMany(mappedBy = "nutritiousFood")
    private List<FoodType> foodTypes;
}
