package com.project.petmanagement.activity.schedule.inject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.petmanagement.R;
import com.project.petmanagement.activity.MainActivity;
import com.project.petmanagement.activity.schedule.inject.SelectPetToVaccineActivity;
import com.project.petmanagement.adapters.ListScheduleVaccineAdapter;
import com.project.petmanagement.models.entity.VaccinationNotification;
import com.project.petmanagement.payloads.responses.ListVaccineNotification;
import com.project.petmanagement.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VaccineInjectionScheduleActivity extends AppCompatActivity {
    private RecyclerView scheduleRecyclerView;
    private ListScheduleVaccineAdapter listScheduleVaccineAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_injection_schedule);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        scheduleRecyclerView = findViewById(R.id.list_schedule_recyclerview);
        returnArrow.setOnClickListener(v -> {
            Intent intent = new Intent(VaccineInjectionScheduleActivity.this, MainActivity.class);
            intent.putExtra("fragmentIndex", String.valueOf(0));
            startActivity(intent);
            finish();
        });
        Button addInjectionScheduleBtn = findViewById(R.id.add_injection_schedule_btn);
        addInjectionScheduleBtn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SelectPetToVaccineActivity.class);
            startActivity(intent);
        });
        getListVaccineNotification();
        scheduleRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) {
                    addInjectionScheduleBtn.setVisibility(View.VISIBLE);
                } else {
                    addInjectionScheduleBtn.setVisibility(View.GONE);
                }
            }
        });
    }
    private void getListVaccineNotification(){
        ApiService.apiService.getVaccineNotificationByUser().enqueue(new Callback<ListVaccineNotification>() {
            @Override
            public void onResponse(Call<ListVaccineNotification> call, Response<ListVaccineNotification> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null && response.body().getData()!=null){
                        List<VaccinationNotification> vaccineNotificationList = response.body().getData();
                        listScheduleVaccineAdapter = new ListScheduleVaccineAdapter(vaccineNotificationList, VaccineInjectionScheduleActivity.this);
                        scheduleRecyclerView.setAdapter(listScheduleVaccineAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(VaccineInjectionScheduleActivity.this, LinearLayoutManager.VERTICAL, false);
                        scheduleRecyclerView.setLayoutManager(layoutManager);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListVaccineNotification> call, Throwable t) {
                Toast.makeText(VaccineInjectionScheduleActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListVaccineNotification();
    }
}