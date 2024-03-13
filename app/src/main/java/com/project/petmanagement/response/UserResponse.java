package com.project.petmanagement.response;

import com.project.petmanagement.model.User;

public class UserResponse extends Response{

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private User data;


    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}
