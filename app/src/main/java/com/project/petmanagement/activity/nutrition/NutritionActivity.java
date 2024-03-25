<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/nutrition/NutritionActivity.java
package com.project.petmanagement.activities.nutrition;
========
package com.project.petmanagement.activity.nutrition;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/nutrition/NutritionActivity.java

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
<<<<<<<< HEAD:app/src/main/java/com/project/petmanagement/activities/nutrition/NutritionActivity.java
import com.project.petmanagement.adapters.NutritionsRecyclerViewAdapter;
import com.project.petmanagement.models.FoodType;
import com.project.petmanagement.models.NutritionInfo;
import com.project.petmanagement.dtos.responses.FoodTypeResponse;
import com.project.petmanagement.dtos.responses.NutritionInfoResponse;
========
import com.project.petmanagement.adapter.NutritionsRecyclerViewAdapter;
import com.project.petmanagement.model.FoodType;
import com.project.petmanagement.model.NutritionInfo;
import com.project.petmanagement.payload.response.FoodTypeResponse;
import com.project.petmanagement.payload.response.NutritionInfoResponse;
>>>>>>>> origin/frontend/shop:app/src/main/java/com/project/petmanagement/activity/nutrition/NutritionActivity.java
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutritionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NutritionsRecyclerViewAdapter nutritionAdapter;
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
                    nutritionsInfoList = foodType != null ? foodType.getNutritionsInforList() : null;
                    nutritionAdapter = new NutritionsRecyclerViewAdapter(NutritionActivity.this, nutritionsInfoList);
                    recyclerView.setAdapter(nutritionAdapter);
                }else{
                    getListNutritionInfo(search.getText().toString(), foodType != null ? foodType.getId() : null);
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
                    getListNutritionInfo(key, foodType.getId());
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

        ImageView nutritionImage = findViewById(R.id.nutrition_example);
        nutritionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NutritionDetailActivity.class);
                startActivity(intent);
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
                Log.d("dddd1", String.valueOf(response.code()));
                if(response.isSuccessful()){
                    FoodTypeResponse foodTypeResponse = response.body();
                    if (foodTypeResponse != null && foodTypeResponse.getData() != null) {
                        for(FoodType x: foodTypeResponse.getData()){
                            foodTypes.put(x.getTypeName(),x);
                        }
                        foodTypeAdapter = new ArrayAdapter<>(NutritionActivity.this, R.layout.list_item_dropdown,new ArrayList<>(foodTypes.keySet()));
                        foodTypeView.setAdapter(foodTypeAdapter);
                    }
                    Log.d("debug", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<FoodTypeResponse> call, Throwable t) {
                Toast.makeText(NutritionActivity.this, "Api failed", Toast.LENGTH_SHORT).show();
                Log.d("debug", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
    private void getListNutritionInfo(String key, Long foodTypeId){
        ApiService.apiService.getListNutritionInfo(key, foodTypeId).enqueue(new Callback<NutritionInfoResponse>() {
            @Override
            public void onResponse(Call<NutritionInfoResponse> call, Response<NutritionInfoResponse> response) {
                if(response.isSuccessful()){
                    NutritionInfoResponse nutritionInfoResponse = response.body();
                    nutritionsInfoList = nutritionInfoResponse != null ? nutritionInfoResponse.getData() : new ArrayList<>();
                    nutritionAdapter = new NutritionsRecyclerViewAdapter(NutritionActivity.this, nutritionsInfoList);
                    recyclerView.setAdapter(nutritionAdapter);
                }
            }

            @Override
            public void onFailure(Call<NutritionInfoResponse> call, Throwable t) {

            }
        });
    }
}