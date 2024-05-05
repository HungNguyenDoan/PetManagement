package com.project.petmanagement.activity.schedule.careactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.CareActivityNotificationAdapter;
import com.project.petmanagement.models.entity.CareActivityNotification;
import com.project.petmanagement.payloads.responses.ListCareActivityNotificationResponse;
import com.project.petmanagement.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageCareActivityScheduleInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_schedule_info);
        recyclerView = findViewById(R.id.recyclerview);
        Button addCareActivityScheduleBtn = findViewById(R.id.add_feed_schedule_btn);
        addCareActivityScheduleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SelectPetToActivityActivity.class);
            startActivity(intent);
        });
        getCareActivityNotification();
        ImageView returnArrow = findViewById(R.id.return_arrow);
        returnArrow.setOnClickListener(v -> finish());
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) {
                    addCareActivityScheduleBtn.setVisibility(View.VISIBLE);
                } else {
                    addCareActivityScheduleBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    private void getCareActivityNotification() {
        ApiService.apiService.getAllCareActivityNotificationByUser().enqueue(new Callback<ListCareActivityNotificationResponse>() {
            @Override
            public void onResponse(Call<ListCareActivityNotificationResponse> call, Response<ListCareActivityNotificationResponse> response) {
                if (response.isSuccessful()) {
                    ListCareActivityNotificationResponse careActivityNotificationResponse = response.body();
                    if (careActivityNotificationResponse != null) {
                        List<CareActivityNotification> careActivityNotificationList = careActivityNotificationResponse.getData();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(ManageCareActivityScheduleInfoActivity.this, RecyclerView.VERTICAL, false);
                        CareActivityNotificationAdapter careActivityNotificationAdapter = new CareActivityNotificationAdapter(careActivityNotificationList, ManageCareActivityScheduleInfoActivity.this);
                        recyclerView.setAdapter(careActivityNotificationAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListCareActivityNotificationResponse> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getCareActivityNotification();
    }
}