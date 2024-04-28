package com.project.petmanagement.activity.nutrition;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.NutritiousFood;

public class NutritionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_detail);
        NutritiousFood nutritiousFood = (NutritiousFood) getIntent().getSerializableExtra("nutritiousFood");
        if(nutritiousFood!=null){

        }
    }
}