package com.project.petmanagement.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.dailyLog.AddDailyLog;
import com.project.petmanagement.adapters.DailyActivityLogAdapter;
import com.project.petmanagement.models.entity.DailyActivityLog;
import com.project.petmanagement.payloads.requests.DailyActivityLogRequest;
import com.project.petmanagement.payloads.responses.ListDailyActivityLogResponse;
import com.project.petmanagement.services.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetActivityFragment extends Fragment {
    private List<DailyActivityLog> dailyActivityLogList;
    private RecyclerView recyclerView;
    private FloatingActionButton addBtn;
    private long petId;
    public PetActivityFragment(long petId) {
        this.petId = petId;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_activity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.daily_log_recycler_view);
        addBtn = view.findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddDailyLog.class);
                intent.putExtra("petId", petId);
                startActivity(intent);
            }
        });
        getDailyActivityLogByPet();
    }

    public void getDailyActivityLogByPet(){
        ApiService.apiService.getDailyLogsByPet(petId).enqueue(new Callback<ListDailyActivityLogResponse>() {
            @Override
            public void onResponse(Call<ListDailyActivityLogResponse> call, Response<ListDailyActivityLogResponse> response) {
                if(response.isSuccessful()){
                    ListDailyActivityLogResponse listDailyActivityLogResponse = response.body();
                    if(listDailyActivityLogResponse !=null){
                        dailyActivityLogList = listDailyActivityLogResponse.getData();
                        DailyActivityLogAdapter dailyActivityLogAdapter = new DailyActivityLogAdapter(requireActivity(), dailyActivityLogList);
                        LinearLayoutManager layoutManager = new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false);
                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
                        recyclerView.setAdapter(dailyActivityLogAdapter);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.addItemDecoration(dividerItemDecoration);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListDailyActivityLogResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDailyActivityLogByPet();
    }
}