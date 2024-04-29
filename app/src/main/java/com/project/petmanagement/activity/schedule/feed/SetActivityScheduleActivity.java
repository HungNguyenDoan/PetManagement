package com.project.petmanagement.activity.schedule.feed;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.schedule.inject.SelectPetToVaccineActivity;
import com.project.petmanagement.activity.schedule.inject.SetVaccineScheduleActivity;
import com.project.petmanagement.models.entity.Pet;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetActivityScheduleActivity extends AppCompatActivity {
    private Button btnSelectPet;
    private Pet pet;
    private CircleImageView imagePet;
    private TextView namePet;
    private ActivityResultLauncher<Intent> launcherPet = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK){
                Intent data = o.getData();
                if (data != null) {
                    pet = (Pet) data.getSerializableExtra("pet");
                    setInfoPet();
                }
            }
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity_schedule);
        CardView sefActivityInfo = findViewById(R.id.set_activity_info);
        btnSelectPet = findViewById(R.id.return_choose_pet);
        imagePet = findViewById(R.id.image_pet);
        namePet = findViewById(R.id.name_pet);
        pet = (Pet) getIntent().getSerializableExtra("pet");
        if(pet!=null){
            setInfoPet();
        }
        btnSelectPet.setOnClickListener(v -> {
            Intent intent = new Intent(SetActivityScheduleActivity.this, SelectPetToActivityActivity.class);
            intent.putExtra("action", "reselect");
            launcherPet.launch(intent);
        });
        sefActivityInfo.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetActivityInfoActivity.class);
            startActivity(intent);
        });

        CardView setActivityNotification = findViewById(R.id.set_activity_notification);
        setActivityNotification.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SetActivityNotificationActivity.class);
            startActivity(intent);
        });

        Button saveActivityScheduleBtn = findViewById(R.id.save_activity_schedule_btn);
        saveActivityScheduleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), ActivityScheduleInfoActivity.class);
            startActivity(intent);
        });

        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(v -> finish());
    }
    private void setInfoPet(){
        if(pet!=null){
            namePet.setText(pet.getFullName());
            Glide.with(SetActivityScheduleActivity.this)
                    .load(pet.getImage())
                    .error(R.drawable.default_pet_no_image)
                    .into(imagePet);
        }
    }
}