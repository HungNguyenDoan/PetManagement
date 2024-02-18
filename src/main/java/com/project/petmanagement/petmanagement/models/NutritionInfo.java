package com.project.petmanagement.petmanagement.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "nutrition_type", joinColumns = @JoinColumn(name = "nutrition_id"), inverseJoinColumns = @JoinColumn(name = "foodtype_id"))
    private final List<FoodType> listFoodTypes = new ArrayList<>();

    public void addFoodType(FoodType foodType) {
        this.listFoodTypes.add(foodType);
        foodType.getListNutritionInfos().add(this);
    }

    public void removeFoodType(FoodType foodType) {
        this.listFoodTypes.remove(foodType);
        foodType.getListNutritionInfos().remove(this);
    }
}
