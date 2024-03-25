<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/dtos/responses/UserResponse.java
package com.project.petmanagement.dtos.responses;
========
package com.project.petmanagement.payload.response;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/payload/response/UserResponse.java

import com.project.petmanagement.models.User;

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
