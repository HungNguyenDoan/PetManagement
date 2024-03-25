<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/DiseasesDetailActivity.java
package com.project.petmanagement.activities;
========
package com.project.petmanagement.activity.diseases;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/diseases/DiseasesDetailActivity.java

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.project.petmanagement.R;

public class DiseasesDetailActivity extends AppCompatActivity {

    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases_detail2);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}