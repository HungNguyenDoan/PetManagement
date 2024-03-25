<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/nutrition/NutritionDetailActivity.java
package com.project.petmanagement.activities.nutrition;
========
package com.project.petmanagement.activity.nutrition;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/nutrition/NutritionDetailActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.petmanagement.R;

public class NutritionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_detail);
        ImageView backBtn = findViewById(R.id.btn_back);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}