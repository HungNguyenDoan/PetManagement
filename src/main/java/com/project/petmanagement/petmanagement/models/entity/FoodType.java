package com.project.petmanagement.petmanagement.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "food_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    // FoodType có quan hệ n - n với NutritiousFood
    @ManyToMany
    @JoinTable(
            name = "food_types_nutritious_food",
            joinColumns = @JoinColumn(name = "food_type_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "nutritious_food_id", referencedColumnName = "id"))
    private List<NutritiousFood> nutritiousFoodList;
}
