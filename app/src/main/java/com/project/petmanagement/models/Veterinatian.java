package com.project.petmanagement.models;

import java.io.Serializable;
import java.util.Date;

public class Veterinatian implements Serializable {
    private Long id;
    private String fullName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String email;
    private String specialization;
    private Float rate;
    private Integer experience;
    private String avatar;
    private Integer peopleRate;

    public Veterinatian(Long id, String fullName, String phoneNumber,  String email, String specialization, float rate, Integer experience, String avatar, Integer peopleRate) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;

        this.email = email;
        this.specialization = specialization;
        this.rate = rate;
        this.experience = experience;
        this.avatar = avatar;
        this.peopleRate = peopleRate;
    }


    public Integer getPeopleRate() {
        return peopleRate;
    }

    public void setPeopleRate(Integer peopleRate) {
        this.peopleRate = peopleRate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }
}
