package com.project.petmanagement.response;

import com.project.petmanagement.model.NutritionInfo;

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
