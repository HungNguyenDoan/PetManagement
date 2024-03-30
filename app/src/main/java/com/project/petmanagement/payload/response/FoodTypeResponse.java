package com.project.petmanagement.payload.response;

import com.project.petmanagement.models.FoodType;

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
