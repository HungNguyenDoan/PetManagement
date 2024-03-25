<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/dtos/responses/Response.java
package com.project.petmanagement.dtos.responses;
========
package com.project.petmanagement.payload.response;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/payload/response/Response.java

public class Response {
    private String message;
    private Integer status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
