package com.project.petmanagement.payload.response;

import com.project.petmanagement.models.Pet;

public class PetResponse extends Response{
    private Pet data;

    public Pet getData() {
        return data;
    }

    public void setData(Pet data) {
        this.data = data;
    }
}
