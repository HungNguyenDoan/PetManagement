package com.project.petmanagement.activity.schedule.inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.Pet;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetVaccineScheduleActivity extends AppCompatActivity {
    private Pet pet;
    private CircleImageView imagePet;
    private TextView namePet;
    private Button choosePet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_vaccine_schedule);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        imagePet = findViewById(R.id.image_pet);
        namePet = findViewById(R.id.name_pet);
        pet = (Pet) getIntent().getSerializableExtra("pet");
        if(pet!=null){
            namePet.setText(pet.getFullName());
            Glide.with(SetVaccineScheduleActivity.this)
                    .load(pet.getImage())
                    .error(R.drawable.default_pet_no_image)
                    .into(imagePet);
        }
        returnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CardView setInjectionNotificationCardView = findViewById(R.id.set_injection_notification);
        setInjectionNotificationCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SetVaccineNotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}