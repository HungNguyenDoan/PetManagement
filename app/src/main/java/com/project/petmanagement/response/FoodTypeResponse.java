package com.project.petmanagement.response;

import com.project.petmanagement.model.FoodType;

import java.util.List;

public class FoodTypeResponse extends Response{
    private List<FoodType> data;

    public List<FoodType> getData() {
        return data;
    }

    public void setData(List<FoodType> data) {
        this.data = data;
    }
}
