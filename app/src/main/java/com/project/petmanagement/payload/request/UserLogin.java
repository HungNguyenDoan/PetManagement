<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/models/UserLogin.java
package com.project.petmanagement.models;
========
package com.project.petmanagement.payload.request;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/payload/request/UserLogin.java

public class UserLogin {
    private  String username;
    private String password;

    public UserLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
