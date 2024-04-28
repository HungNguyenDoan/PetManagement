//package com.project.petmanagement.activity.schedule.inject;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import com.project.petmanagement.R;
//
//public class PetInjectionActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pet_injecttion);
//        ImageView returnArrow = findViewById(R.id.return_arrow);
//        returnArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        ImageView vaccineInjectBtn = findViewById(R.id.vaccine_image);
//        vaccineInjectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), VaccineInjectionScheduleActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//}