package com.project.petmanagement.petmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "nutrition_info")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NutritionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nutrition_name")
    private String nutritionName;
    @Column(name = "description", length = 1000)
    private String description;
    @Column(name = "source_url")
    private String source_url;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "foodtype_id", referencedColumnName = "id", nullable = false)
    private FoodType foodType;
    @JsonProperty("foodtype_name")
    private String getFoodType() {
        return foodType.getTypeName();
    }
}
