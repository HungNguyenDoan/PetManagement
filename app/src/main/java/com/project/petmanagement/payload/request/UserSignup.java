<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/models/UserSignup.java
package com.project.petmanagement.models;
========
package com.project.petmanagement.payload.request;

import com.google.gson.annotations.SerializedName;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/payload/request/UserSignup.java

import java.util.Date;

public class UserSignup {
    private String fullName;
    private String password;
    private String phonenumber;
    private String email;
    private String address;
    private Date dob;
    public UserSignup(String fullName, String password, String phonenumber, String email, String address, Date dob) {
        this.fullName = fullName;
        this.password = password;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.dob = dob;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
