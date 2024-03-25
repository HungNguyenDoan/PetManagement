<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/ScheduleActivity.java
package com.project.petmanagement.activities;
========
package com.project.petmanagement.activity.schedule;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/ScheduleActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.project.petmanagement.R;
<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/ScheduleActivity.java
import com.project.petmanagement.activities.injection.PetInjectionActivity;
========
import com.project.petmanagement.activity.schedule.inject.PetInjectionActivity;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/ScheduleActivity.java

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                Intent intent = new Intent(getApplicationContext(), PetInjectionActivity.class);
                startActivity(intent);
            }
        });
    }
}