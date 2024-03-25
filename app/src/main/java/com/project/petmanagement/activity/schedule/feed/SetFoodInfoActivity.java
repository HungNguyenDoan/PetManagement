<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/feed/SetFoodInfoActivity.java
package com.project.petmanagement.activities.feed;
========
package com.project.petmanagement.activity.schedule.feed;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/feed/SetFoodInfoActivity.java

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.petmanagement.R;
<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/feed/SetFoodInfoActivity.java
import com.project.petmanagement.activities.SetFeedScheduleActivity2;
========
import com.project.petmanagement.activity.schedule.feed.SetFeedScheduleActivity2;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/feed/SetFoodInfoActivity.java

public class SetFoodInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_food_info);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button saveBtn = findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetFeedScheduleActivity2.class);
                startActivity(intent);
            }
        });
    }
}