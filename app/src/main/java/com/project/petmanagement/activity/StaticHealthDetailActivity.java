package com.project.petmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.project.petmanagement.R;

import java.util.Arrays;

public class StaticHealthDetailActivity extends AppCompatActivity {
    private Spinner spinner;
    private ArrayAdapter<String> exerciseAdapter;
    private String[] exerciseLevel = {"1","2", "3", "4", "5"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_health_detail);
        spinner = findViewById(R.id.exercise_level);
        exerciseAdapter = new ArrayAdapter<>(this, R.layout.list_item_dropdown, Arrays.asList(exerciseLevel));
        spinner.setAdapter(exerciseAdapter);
    }
}