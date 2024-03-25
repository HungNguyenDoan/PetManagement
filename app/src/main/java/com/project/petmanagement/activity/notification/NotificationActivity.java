<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/NotificationActivity.java
package com.project.petmanagement.activities;
========
package com.project.petmanagement.activity.notification;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/notification/NotificationActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.petmanagement.R;

public class NotificationActivity extends AppCompatActivity {
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}