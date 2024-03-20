package com.project.petmanagement.activity.nutrition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.adapter.NutritionsRecyclerViewAdapter;
import com.project.petmanagement.model.FoodType;
import com.project.petmanagement.model.NutritionInfo;
import com.project.petmanagement.response.FoodTypeResponse;
import com.project.petmanagement.response.NutritionInfoResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutritionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NutritionsRecyclerViewAdapter nutritionsAdapter;
    private List<NutritionInfo> nutritionsInfoList;
    private AutoCompleteTextView foodTypeView;
    private ArrayAdapter<String> foodTypeAdapter;
    private TextInputEditText search;
    private ImageView btnBack;
    private Map<String, FoodType> foodTypes;
    private final StorageService storageService = MyApplication.getStorageService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritions);
        findViewById();
        nutritionsInfoList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        foodTypes = new LinkedHashMap<>();
        getALlFoodType();
        foodTypeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String foodTypeName = foodTypeAdapter.getItem(position);
                FoodType foodType = foodTypes.get(foodTypeName);
                if(search.getText().toString().isEmpty()){
                    nutritionsInfoList = foodType.getNutritionsInforList();
                    nutritionsAdapter= new NutritionsRecyclerViewAdapter(NutritionActivity.this, nutritionsInfoList);
                    recyclerView.setAdapter(nutritionsAdapter);
                }else{
                    getListNutritionInfor(search.getText().toString(), foodType.getId());
                }

            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String key = s.toString();
                FoodType foodType = foodTypes.get(foodTypeView.getText().toString());
                if(foodType!=null){
                    getListNutritionInfor(key, foodType.getId());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void findViewById(){
        recyclerView = findViewById(R.id.recyclerview);
        foodTypeView = findViewById(R.id.foot_type);
        btnBack = findViewById(R.id.btn_back);
        search = findViewById(R.id.search);
    }
    private void getALlFoodType(){
        ApiService.apiService.getAllFoodType().enqueue(new Callback<FoodTypeResponse>() {
            @Override
            public void onResponse(Call<FoodTypeResponse> call, Response<FoodTypeResponse> response) {
                Log.d("dddd1",response.code()+"");
                if(response.isSuccessful()){
                    FoodTypeResponse foodTypeResponse = response.body();
                    if (foodTypeResponse != null && foodTypeResponse.getData() != null) {
                        for(FoodType x: foodTypeResponse.getData()){
                            foodTypes.put(x.getTypeName(),x);
                        }
                        foodTypeAdapter = new ArrayAdapter<>(NutritionActivity.this, R.layout.list_item_dropdown,new ArrayList<>(foodTypes.keySet()));
                        foodTypeView.setAdapter(foodTypeAdapter);
                    }
                    Log.d("ddddd", response.code()+"");
                }
            }

            @Override
            public void onFailure(Call<FoodTypeResponse> call, Throwable t) {
                Toast.makeText(NutritionActivity.this, "Api failed", Toast.LENGTH_SHORT).show();
                Log.d("ddddd", t.getMessage());
            }
        });
    }
    private void getListNutritionInfor(String key, Long foodTypeId){
        ApiService.apiService.getListNutritionInfo(key, foodTypeId).enqueue(new Callback<NutritionInfoResponse>() {
            @Override
            public void onResponse(Call<NutritionInfoResponse> call, Response<NutritionInfoResponse> response) {
                if(response.isSuccessful()){
                    NutritionInfoResponse nutritionInfoResponse = response.body();
                    nutritionsInfoList = nutritionInfoResponse.getData();
                    nutritionsAdapter= new NutritionsRecyclerViewAdapter(NutritionActivity.this, nutritionsInfoList);
                    recyclerView.setAdapter(nutritionsAdapter);
                }
            }

            @Override
            public void onFailure(Call<NutritionInfoResponse> call, Throwable t) {

            }
        });
    }
}