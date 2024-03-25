<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/injection/SetInjectionScheduleActivity.java
package com.project.petmanagement.activities.injection;
========
package com.project.petmanagement.activity.schedule.inject;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/inject/SetInjectionScheduleActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.project.petmanagement.R;

public class SetInjectionScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_injection_schedule);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        CardView setInjectionInfoCardView = findViewById(R.id.set_injection_info);
        setInjectionInfoCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), InjectionInfoActivity.class);
                startActivity(intent);
            }
        });

        CardView setInjectionNotificationCardView = findViewById(R.id.set_injection_notification);
        setInjectionNotificationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetInjectNotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}