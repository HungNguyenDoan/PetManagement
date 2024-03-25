<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/SetFeedScheduleActivity2.java
package com.project.petmanagement.activities;
========
package com.project.petmanagement.activity.schedule.feed;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/feed/SetFeedScheduleActivity2.java

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.petmanagement.R;
import com.project.petmanagement.activities.feed.FeedScheduleInfoActivity;
import com.project.petmanagement.activities.feed.SetFeedNotificationActivity;
import com.project.petmanagement.activities.feed.SetFoodInfoActivity;

public class SetFeedScheduleActivity2 extends AppCompatActivity {
    private Button btnSelectPet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_feed_schedule2);
        CardView sefFeedInfo = findViewById(R.id.set_feed_info);
        btnSelectPet = findViewById(R.id.btn_select_pet);
        btnSelectPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetFeedScheduleActivity2.this, SelectPetToFeedActivity.class);
                startActivity(intent);
            }
        });
        sefFeedInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetFoodInfoActivity.class);
                startActivity(intent);
            }
        });

        CardView setFeedNotification = findViewById(R.id.set_feed_notification);
        setFeedNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetFeedNotificationActivity.class);
                startActivity(intent);
            }
        });

        Button saveFeedScheduleBtn = findViewById(R.id.save_feed_schedule_btn);
        saveFeedScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedScheduleInfoActivity.class);
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