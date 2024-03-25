<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/injection/PetInjectionActivity.java
package com.project.petmanagement.activities.injection;
========
package com.project.petmanagement.activity.schedule.inject;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/schedule/inject/PetInjectionActivity.java

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.project.petmanagement.R;

public class PetInjectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_injecttion);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ImageButton drugInjectBtn = findViewById(R.id.drug_inject_btn);
        drugInjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PetInjectionScheduleActivity.class);
                startActivity(intent);
            }
        });
        ImageButton vaccinInjectBtn = findViewById(R.id.vaccine_inject_btn);
        vaccinInjectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VaccineInjectionScheduleActivity.class);
                startActivity(intent);
            }
        });

    }
}