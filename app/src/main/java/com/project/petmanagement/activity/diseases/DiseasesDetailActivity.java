package com.project.petmanagement.activity.diseases;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.Disease;

public class DiseasesDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    private TextView textNameDisease;
    private TextView textDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_detail2);
        btnBack = findViewById(R.id.btn_back);
        Disease disease = (Disease) getIntent().getSerializableExtra("disease");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}