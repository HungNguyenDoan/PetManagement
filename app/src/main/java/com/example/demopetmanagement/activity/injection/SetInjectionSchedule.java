package com.example.demopetmanagement.activity.injection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.demopetmanagement.R;

import java.util.Objects;

public class SetInjectionSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
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
                Intent intent = new Intent(getApplicationContext(), SetDrugInfo.class);
                startActivity(intent);
            }
        });

        CardView setInjectionNotificationCardView = findViewById(R.id.set_injection_notification);
        setInjectionNotificationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetInjectionNotification.class);
                startActivity(intent);
            }
        });
    }
}