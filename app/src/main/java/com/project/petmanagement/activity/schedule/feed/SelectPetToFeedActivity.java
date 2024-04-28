package com.project.petmanagement.activity.schedule.feed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.petmanagement.R;

public class SelectPetToFeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet_to_feed);
        Button continueBtn = findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetFeedScheduleActivity2.class);
            startActivity(intent);
        });

        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(v -> finish());
    }
}