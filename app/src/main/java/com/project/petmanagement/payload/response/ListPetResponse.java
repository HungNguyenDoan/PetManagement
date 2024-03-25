package com.project.petmanagement.payload.response;

import com.project.petmanagement.model.Pet;

import java.util.List;

public class ListPetResponse extends Response{
    private List<Pet> data;

    public List<Pet> getData() {
        return data;
    }

    public void setData(List<Pet> data) {
        this.data = data;
    }
}
