<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/feed/FeedScheduleInfoActivity.java
package com.project.petmanagement.activities.feed;
========
package com.project.petmanagement.activity.schedule.feed;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/feed/FeedScheduleInfoActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.petmanagement.R;

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