package com.example.demopetmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.demopetmanagement.R;

import java.util.Objects;

public class HealthRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_health_record);
    }
}