package com.project.petmanagement.activity.schedule.vaccine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapters.ListPetSelectAdapter;
import com.project.petmanagement.payloads.responses.ListPetResponse;
import com.project.petmanagement.services.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectPetToVaccineActivity extends AppCompatActivity {
    private RecyclerView listPetRecyclerView;
    private ListPetSelectAdapter listPetSelectAdapter;
    private String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pet_to_vaccine);
        ImageView returnArrow = findViewById(R.id.return_arrow);
        listPetRecyclerView = findViewById(R.id.pet_list_recycler_view);
        returnArrow.setOnClickListener(v -> finish());
        action = getIntent().getStringExtra("action");
        getListPet();

    }
    private void getListPet(){
        ApiService.apiService.getAllPetUser().enqueue(new Callback<ListPetResponse>() {
            @Override
            public void onResponse(Call<ListPetResponse> call, Response<ListPetResponse> response) {
                if(response.isSuccessful()){
                    if(response.body()!=null && response.body().getData()!=null){
                        listPetSelectAdapter = new ListPetSelectAdapter(response.body().getData(), SelectPetToVaccineActivity.this,action);
                        listPetRecyclerView.setAdapter(listPetSelectAdapter);
                        listPetRecyclerView.setLayoutManager(new LinearLayoutManager(SelectPetToVaccineActivity.this, LinearLayoutManager.VERTICAL, false));
                        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(SelectPetToVaccineActivity.this, DividerItemDecoration.VERTICAL);
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