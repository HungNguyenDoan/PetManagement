<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/dtos/responses/FoodTypeResponse.java
package com.project.petmanagement.dtos.responses;
========
package com.project.petmanagement.payload.response;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/payload/response/FoodTypeResponse.java

import com.project.petmanagement.models.FoodType;

import java.util.List;

public class FoodTypeResponse extends Response{
    private List<FoodType> data;

    public List<FoodType> getData() {
        return data;
    }

    public void setData(List<FoodType> data) {
        this.data = data;
    }
}
