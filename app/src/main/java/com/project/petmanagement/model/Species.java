package com.project.petmanagement.model;

import java.util.List;

public class Species {
    private Long id;
    private String name;
    private List<Species> breed;
    public Species(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Species> getBreed() {
        return breed;
    }

    public void setBreed(List<Species> breed) {
        this.breed = breed;
    }
}
