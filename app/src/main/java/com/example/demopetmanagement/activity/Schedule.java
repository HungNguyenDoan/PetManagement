package com.example.demopetmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.demopetmanagement.R;
import com.example.demopetmanagement.activity.feed.FeedScheduleInfo;
import com.example.demopetmanagement.activity.injection.SelectInjectionType;
import com.example.demopetmanagement.activity.walk.WalkScheduleInfo;

import java.util.Objects;

public class Schedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_schedule);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        LinearLayout injectActivity = findViewById(R.id.inject_activity);
        injectActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectInjectionType.class);
                startActivity(intent);
            }
        });

        LinearLayout feedActivity = findViewById(R.id.feed_activity);
        feedActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedScheduleInfo.class);
                startActivity(intent);
            }
        });

        LinearLayout walkActivity = findViewById(R.id.walk_activity);
        walkActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WalkScheduleInfo.class);
                startActivity(intent);
            }
        });
    }
}