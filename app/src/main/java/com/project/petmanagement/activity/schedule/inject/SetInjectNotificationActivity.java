<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/injection/SetInjectNotificationActivity.java
package com.project.petmanagement.activities.injection;
========
package com.project.petmanagement.activity.schedule.inject;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/inject/SetInjectNotificationActivity.java

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.project.petmanagement.R;

public class SetInjectNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_inject_notification);
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
                finish();
            }
        });
    }
}