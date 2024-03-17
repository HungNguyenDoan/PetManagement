package com.project.petmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.petmanagement.R;

import java.util.Objects;
public class FeedScheduleInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_schedule_info);
        Button addFeedScheduleBtn = findViewById(R.id.add_feed_schedule_btn);
        addFeedScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectPetToFeedActivity.class);
                startActivity(intent);
            }
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