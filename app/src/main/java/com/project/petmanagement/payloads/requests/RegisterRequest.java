package com.project.petmanagement.payloads.requests;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class RegisterRequest {
    @SerializedName("full_name")
    private String fullName;
    @SerializedName("date_of_birth")
    private Date dateOfBirth;
    @SerializedName("phone_number")
    private String phoneNumber;
    private String email;
    private String address;
    private String avatar;
    private String password;

    public RegisterRequest(String fullName, Date dateOfBirth, String phoneNumber, String email, String address,  String password) {
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
