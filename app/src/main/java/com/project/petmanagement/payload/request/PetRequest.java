package com.project.petmanagement.payload.request;

import java.util.Date;

public class PetRequest {
    private String fullname;
    private Date dob;
    private Long speciesId;
    private String description;
    private Integer gender;
    private String avatar;
    private Integer neutered;

    public PetRequest(String fullname, Date dateOfBirth,  Integer gender, Integer neutered, String avatar, Long speciesId) {
        this.fullname = fullname;
        this.dob = dateOfBirth;
        this.gender = gender;
        this.neutered = neutered;
        this.avatar = avatar;
        this.speciesId = speciesId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long speciesId) {
        this.speciesId = speciesId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getNeutered() {
        return neutered;
    }

    public void setNeutered(Integer neutered) {
        this.neutered = neutered;
    }
}
