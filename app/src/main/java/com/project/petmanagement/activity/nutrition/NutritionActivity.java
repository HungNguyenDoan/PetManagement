package com.project.petmanagement.activity.nutrition;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.R;
import com.project.petmanagement.adapters.NutritionsRecyclerViewAdapter;
import com.project.petmanagement.models.entity.FoodType;
import com.project.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.models.entity.Species;
import com.project.petmanagement.payloads.responses.ListFoodTypeResponse;
import com.project.petmanagement.payloads.responses.ListNutritiousFoodResponse;
import com.project.petmanagement.payloads.responses.ListSpeciesResponse;
import com.project.petmanagement.services.ApiService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NutritionActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NutritionsRecyclerViewAdapter nutritionsAdapter;
    private List<NutritiousFood> nutritiousFoodList;
    private AutoCompleteTextView foodTypeView;
    private AutoCompleteTextView speciesView;
    private ArrayAdapter<String> foodTypeAdapter;
    private ArrayAdapter<String> speciesArrayAdapter;
    private TextInputEditText search;
    private ImageView btnBack;
    private Map<String, FoodType> foodTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritions);
        findViewById();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        foodTypes = new LinkedHashMap<>();
        getAllSpecies();
        getALlFoodType();
        getAllNutritiousFood();
//        speciesView.setOnItemClickListener((parent, view, position, id) -> {
//            String speciesName = speciesArrayAdapter.getItem(position);
//            if(speciesName.equals("All")||speciesName.isEmpty()){
//                for(NutritiousFood nutritiousFood: nutritiousFoodList){
//                    if(nutritiousFood.getSpecies().)
//                }
//            }
//        });
//        foodTypeView.setOnItemClickListener((parent, view, position, id) -> {
//            String foodTypeName = foodTypeAdapter.getItem(position);
//            FoodType foodType = foodTypes.get(foodTypeName);
//            if(search.getText().toString().isEmpty()){
//                List<NutritiousFood> nutritiousFoods = foodType.getNutritiousFoodList();
//                nutritionsAdapter= new NutritionsRecyclerViewAdapter(NutritionActivity.this, nutritionsInfoList);
//                recyclerView.setAdapter(nutritionsAdapter);
//            }else{
//                getListNutritionInfor(search.getText().toString(), foodType.getId());
//            }
//
//        });
//        search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                String key = s.toString();
//                FoodType foodType = foodTypes.get(foodTypeView.getText().toString());
//                if(foodType!=null){
//                    getListNutritionInfor(key, foodType.getId());
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
        btnBack.setOnClickListener(v -> finish());
    }

    private void findViewById() {
        recyclerView = findViewById(R.id.recyclerview);
        foodTypeView = findViewById(R.id.foot_type);
        speciesView = findViewById(R.id.species);
        btnBack = findViewById(R.id.btn_back);
        search = findViewById(R.id.search);
    }
    private void getAllSpecies(){
        ApiService.apiService.getSpecies().enqueue(new Callback<ListSpeciesResponse>() {
            @Override
            public void onResponse(Call<ListSpeciesResponse> call, Response<ListSpeciesResponse> response) {
                if(response.isSuccessful()){
                    ListSpeciesResponse listSpeciesResponse = response.body();
                    if(listSpeciesResponse!=null&& listSpeciesResponse.getData()!=null){
                        List<String> speciesName = new ArrayList<>();
                        speciesName.add("All");
                        for(Species species: listSpeciesResponse.getData()){
                            speciesName.add(species.getName());
                        }
                        foodTypeAdapter = new ArrayAdapter<>(NutritionActivity.this, R.layout.list_item_dropdown, new ArrayList<>(speciesName));
                    }
                }
            }

            @Override
            public void onFailure(Call<ListSpeciesResponse> call, Throwable t) {

            }
        });
    }
    private void getALlFoodType() {
        ApiService.apiService.getAllFoodType().enqueue(new Callback<ListFoodTypeResponse>() {
            @Override
            public void onResponse(Call<ListFoodTypeResponse> call, Response<ListFoodTypeResponse> response) {
                if (response.isSuccessful()) {
                    ListFoodTypeResponse foodTypeResponse = response.body();
                    if (foodTypeResponse != null && foodTypeResponse.getData() != null) {
                        for (FoodType x : foodTypeResponse.getData()) {
                            foodTypes.put(x.getName(), x);
                        }
                        foodTypeAdapter = new ArrayAdapter<>(NutritionActivity.this, R.layout.list_item_dropdown, new ArrayList<>(foodTypes.keySet()));
                        foodTypeView.setAdapter(foodTypeAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListFoodTypeResponse> call, Throwable t) {
                Toast.makeText(NutritionActivity.this, "Api failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getAllNutritiousFood() {
        ApiService.apiService.getAllNutritious().enqueue(new Callback<ListNutritiousFoodResponse>() {
            @Override
            public void onResponse(Call<ListNutritiousFoodResponse> call, Response<ListNutritiousFoodResponse> response) {
                if (response.isSuccessful()) {
                    ListNutritiousFoodResponse listNutritiousFoodResponse = response.body();
                    if (listNutritiousFoodResponse != null && listNutritiousFoodResponse.getData() != null) {
                        nutritiousFoodList = listNutritiousFoodResponse.getData();
                        nutritionsAdapter = new NutritionsRecyclerViewAdapter(NutritionActivity.this, nutritiousFoodList);
                        recyclerView.setAdapter(nutritionsAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListNutritiousFoodResponse> call, Throwable t) {
                Toast.makeText(NutritionActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}