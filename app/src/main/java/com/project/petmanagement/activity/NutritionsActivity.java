package com.project.petmanagement.activity;

import static com.project.petmanagement.R.color.green;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.project.petmanagement.R;
import com.project.petmanagement.adapter.NutritionsRecyclerViewAdapter;
import com.project.petmanagement.model.FoodType;
import com.project.petmanagement.model.Image;
import com.project.petmanagement.model.NutritionsInfor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NutritionsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private NutritionsRecyclerViewAdapter nutritionsAdapter;
    private AutoCompleteTextView foodTypeView;
    private TextInputLayout foodTypeLayout;
    ArrayAdapter<String> foodTypeAdapter;
    private ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutritions);
        findViewById();
        nutritionsAdapter = new NutritionsRecyclerViewAdapter(this, getList());
        recyclerView.setAdapter(nutritionsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        List<FoodType> foodTypes = getFoodType();
        List<String> typeNames = new ArrayList<>();
        for(FoodType foodType: foodTypes){
            typeNames.add(foodType.getTypeName());
        }
        foodTypeAdapter = new ArrayAdapter<>(this,R.layout.list_item_dropdown, typeNames);
        foodTypeView.setAdapter(foodTypeAdapter);
        foodTypeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int color = ContextCompat.getColor(NutritionsActivity.this,R.color.green);
                foodTypeLayout.setBoxStrokeColor(color);
                foodTypeLayout.setHintTextColor(ColorStateList.valueOf(color));
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
        foodTypeLayout = findViewById(R.id.foot_type_layout);
        btnBack = findViewById(R.id.btn_back);
    }
    private List<NutritionsInfor> getList(){
        List<NutritionsInfor> nutritionsInfors = new ArrayList<>();
        nutritionsInfors.add(new NutritionsInfor(1L, "NutriPaws Chicken Delight","Canned wet food with succulent chicken pieces in savory gravy."));
        nutritionsInfors.add(new NutritionsInfor(2L, "NutriPaws Chicken Delight","Canned wet food with succulent chicken pieces in savory gravy."));
        nutritionsInfors.add(new NutritionsInfor(3L, "NutriPaws Chicken Delight","Canned wet food with succulent chicken pieces in savory gravy."));
        nutritionsInfors.add(new NutritionsInfor(4L, "NutriPaws Chicken Delight","Canned wet food with succulent chicken pieces in savory gravy."));
        return nutritionsInfors;
    }
    private List<FoodType> getFoodType(){
        List<FoodType> foodTypes = new ArrayList<>();
        foodTypes.add(new FoodType(1L, "Dry Kibble"));
        foodTypes.add(new FoodType(2L, "Canned Wet Food"));
        foodTypes.add(new FoodType(3L, "Raw Food"));
        foodTypes.add(new FoodType(4L, "Prescription Diets"));
        foodTypes.add(new FoodType(5L, "Dental Chews"));
        foodTypes.add(new FoodType(6L, "Dry Kibble"));
        return foodTypes;
    }
}