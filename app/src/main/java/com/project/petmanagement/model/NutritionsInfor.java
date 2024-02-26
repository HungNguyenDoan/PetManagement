package com.project.petmanagement.model;

public class NutritionsInfor {
    private Long id;

    private String nutritionName;

    private String description;

    private String source_url;
    public NutritionsInfor(Long id, String nutritionName, String description){
        this.id = id;
        this.nutritionName = nutritionName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNutritionName() {
        return nutritionName;
    }

    public void setNutritionName(String nutritionName) {
        this.nutritionName = nutritionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }
}
