package com.project.petmanagement.response;

import com.project.petmanagement.model.Species;

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
