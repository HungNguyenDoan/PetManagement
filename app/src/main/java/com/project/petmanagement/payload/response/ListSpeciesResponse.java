package com.project.petmanagement.payload.response;

import com.project.petmanagement.models.Species;

import java.util.List;

public class ListSpeciesResponse {
    private List<Species> data;
    public List<Species> getData() {
        return data;
    }

    public void setData(List<Species> data) {
        this.data = data;
    }

}
