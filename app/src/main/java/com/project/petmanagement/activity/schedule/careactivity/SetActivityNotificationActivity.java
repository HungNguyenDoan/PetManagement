package com.project.petmanagement.activity.schedule.careactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.project.petmanagement.R;

public class SetActivityNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity_notification);

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetActivityScheduleActivity.class);
            startActivity(intent);
        });

        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}