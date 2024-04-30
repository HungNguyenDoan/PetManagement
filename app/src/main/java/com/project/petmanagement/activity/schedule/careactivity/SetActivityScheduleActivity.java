package com.project.petmanagement.activity.schedule.careactivity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.project.petmanagement.R;
import com.project.petmanagement.models.entity.Pet;
import com.project.petmanagement.payloads.requests.CareActivityInfoRequest;
import com.project.petmanagement.payloads.requests.CareActivityNotificationRequest;
import com.project.petmanagement.payloads.requests.OneTimeScheduleRequest;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SetActivityScheduleActivity extends AppCompatActivity {
    private Button btnSelectPet;
    private Pet pet;
    private CircleImageView imagePet;
    private TextView namePet;
    private CareActivityNotificationRequest  careActivityNotificationRequest;
    private CardView sefActivityInfo;

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
    private ActivityResultLauncher<Intent> launcherCareActivity = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult o) {
            if(o.getResultCode() == RESULT_OK){
                Intent data = o.getData();
                if (data != null) {
                    careActivityNotificationRequest = (CareActivityNotificationRequest) data.getSerializableExtra("careActivityNotificationRequest");
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_activity_schedule);
        sefActivityInfo = findViewById(R.id.set_activity_info);
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
            Intent intent = new Intent(SetActivityScheduleActivity.this, SetActivityInfoActivity.class);
            launcherCareActivity.launch(intent);
        });

        CardView setActivityNotification = findViewById(R.id.set_activity_notification);
        setActivityNotification.setOnClickListener(v -> {
            Intent intent = new Intent(SetActivityScheduleActivity.this, SetActivityNotificationActivity.class);
            startActivity(intent);
        });

        Button saveActivityScheduleBtn = findViewById(R.id.save_activity_schedule_btn);
        saveActivityScheduleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(SetActivityScheduleActivity.this, ActivityScheduleInfoActivity.class);
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