<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/walk/SelectPetToWalkActivity.java
package com.project.petmanagement.activities.walk;
========
package com.project.petmanagement.activity.schedule.walk;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/walk/SelectPetToWalkActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.petmanagement.R;

public class SelectPetToWalkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet_to_walk);
        Button continueBtn = findViewById(R.id.continue_btn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetWalkScheduleActivity.class);
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