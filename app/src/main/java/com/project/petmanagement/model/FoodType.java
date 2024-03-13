package com.project.petmanagement.model;

import java.util.List;

public class FoodType {
    private Long id;
    private String typeName;
    private List<NutritionInfo> listNutrition;

    public FoodType(Long id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public List<NutritionInfo> getNutritionsInforList() {
        return listNutrition;
    }

    public void setNutritionsInforList(List<NutritionInfo> listNutrition) {
        this.listNutrition = listNutrition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
