<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/dtos/responses/NutritionInfoResponse.java
package com.project.petmanagement.dtos.responses;
========
package com.project.petmanagement.payload.response;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/payload/response/NutritionInfoResponse.java

import com.project.petmanagement.models.NutritionInfo;

import java.util.List;

public class NutritionInfoResponse extends Response {
    private List<NutritionInfo> data;

    public List<NutritionInfo> getData() {
        return data;
    }

    public void setData(List<NutritionInfo> data) {
        this.data = data;
    }
}
