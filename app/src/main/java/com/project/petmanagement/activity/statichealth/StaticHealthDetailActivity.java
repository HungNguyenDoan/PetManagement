<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/shop/statistic/StaticHealthDetailActivity.java
package com.project.petmanagement.activities.shop.statistic;
========
package com.project.petmanagement.activity.statichealth;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/statichealth/StaticHealthDetailActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.project.petmanagement.R;

import java.util.Arrays;

public class StaticHealthDetailActivity extends AppCompatActivity {
    private Spinner spinner;
    private ImageView btnBack;
    private ArrayAdapter<String> exerciseAdapter;
    private String[] exerciseLevel = {"1","2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_health_detail);
        spinner = findViewById(R.id.exercise_level);
        exerciseAdapter = new ArrayAdapter<>(this, R.layout.list_item_dropdown, Arrays.asList(exerciseLevel));
        spinner.setAdapter(exerciseAdapter);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}