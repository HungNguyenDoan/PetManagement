package com.project.petmanagement.response;

import com.project.petmanagement.model.Pet;

public class PetResponse extends Response{
    private Pet data;

    public Pet getData() {
        return data;
    }

    public void setData(Pet data) {
        this.data = data;
    }
}
