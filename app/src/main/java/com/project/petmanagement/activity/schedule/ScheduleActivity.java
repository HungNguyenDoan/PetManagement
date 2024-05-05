package com.project.petmanagement.activity.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.schedule.careactivity.ManageCareActivityScheduleInfoActivity;
import com.project.petmanagement.activity.schedule.vaccine.ManageVaccineInjectionScheduleActivity;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(v -> finish());
        LinearLayout careActivity = findViewById(R.id.care_activity);
        LinearLayout injectActivity = findViewById(R.id.inject_activity);
        careActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ManageCareActivityScheduleInfoActivity.class);
            startActivity(intent);
            finish();
        });
        injectActivity.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ManageVaccineInjectionScheduleActivity.class);
            startActivity(intent);
            finish();
        });
    }
}