package com.project.petmanagement.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Pet implements Serializable {
    private Long id;
    private String fullname;
    private Long userId;
    private Date dateOfBirth;
    private Long speciesId;
    private Date createAt;
    private Date updateAt;
    private Integer isActive;
    private Integer gender;
    private Integer neutered;
    private String avatar;
    @SerializedName("species_name")
    private String speciesName;

    public Pet(String fullname, Date dateOfBirth,  Integer gender, Integer neutered, String avatar, Long speciesId) {
        this.fullname = fullname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.neutered = neutered;
        this.avatar = avatar;
        this.speciesId = speciesId;
    }

    public Long getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(Long specesId) {
        this.speciesId = specesId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getNeutered() {
        return neutered;
    }

    public void setNeutered(Integer neutered) {
        this.neutered = neutered;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
