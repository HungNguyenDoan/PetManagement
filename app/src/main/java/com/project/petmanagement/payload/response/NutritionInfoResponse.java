package com.project.petmanagement.payload.response;

import com.project.petmanagement.models.NutritionInfo;

import java.util.List;

public class NutritionInfoResponse extends Response {
    private List<NutritionInfo> data;

    public List<NutritionInfo> getData() {
        return data;
    }

    public void setData(List<NutritionInfo> data) {
        this.data = data;
    }
}
