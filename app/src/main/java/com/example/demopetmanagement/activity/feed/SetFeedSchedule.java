package com.example.demopetmanagement.activity.feed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.demopetmanagement.R;

import java.util.Objects;

public class SetFeedSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_set_feed_schedule);
        CardView sefFeedInfo = findViewById(R.id.set_feed_info);
        sefFeedInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetFoodInfo.class);
                startActivity(intent);
            }
        });

        CardView setFeedNotification = findViewById(R.id.set_feed_notification);
        setFeedNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetFeedNotification.class);
                startActivity(intent);
            }
        });

        Button saveFeedScheduleBtn = findViewById(R.id.save_feed_schedule_btn);
        saveFeedScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedScheduleInfo.class);
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