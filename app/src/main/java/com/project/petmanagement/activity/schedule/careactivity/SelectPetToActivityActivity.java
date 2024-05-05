package com.project.petmanagement.activity.schedule.careactivity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.SelectPetActivityAdapter;
import com.project.petmanagement.payloads.responses.ListPetResponse;
import com.project.petmanagement.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectPetToActivityActivity extends AppCompatActivity {

    private RecyclerView listPetRecyclerView;
    private SelectPetActivityAdapter selectPetActivityAdapter;
    private String action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet_to_activity);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        listPetRecyclerView = findViewById(R.id.pet_list_recycler_view);
        returnArrow.setOnClickListener(v -> finish());
        action = getIntent().getStringExtra("action");
        getListPet();

    }

    private void getListPet() {
        ApiService.apiService.getAllPetUser().enqueue(new Callback<ListPetResponse>() {
            @Override
            public void onResponse(Call<ListPetResponse> call, Response<ListPetResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getData() != null) {
                        selectPetActivityAdapter = new SelectPetActivityAdapter(response.body().getData(), SelectPetToActivityActivity.this, action);
                        listPetRecyclerView.setAdapter(selectPetActivityAdapter);
                        listPetRecyclerView.setLayoutManager(new LinearLayoutManager(SelectPetToActivityActivity.this, LinearLayoutManager.VERTICAL, false));
                        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(SelectPetToActivityActivity.this, DividerItemDecoration.VERTICAL);
                        listPetRecyclerView.addItemDecoration(decoration);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListPetResponse> call, Throwable t) {

            }
        });
    }
}