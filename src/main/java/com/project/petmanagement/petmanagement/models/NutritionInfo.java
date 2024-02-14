package com.project.petmanagement.petmanagement.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column(name = "description")
    private String description;
    @Column(name = "source_url")
    private String source_url;
    @ManyToMany
    @JoinTable(name = "nutrition_type", 
                    joinColumns = @JoinColumn(name = "nutrition_id"), 
                    inverseJoinColumns = @JoinColumn(name = "foodtype_id"))
    private List<FoodType> listFoodTypes;
}
