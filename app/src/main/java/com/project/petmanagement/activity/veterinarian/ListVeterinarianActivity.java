package com.project.petmanagement.activity.veterinarian;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.project.petmanagement.R;
import com.project.petmanagement.adapters.ListVererinarianAdapter;
import com.project.petmanagement.models.Veterinatian;

import java.util.ArrayList;
import java.util.List;

public class ListVeterinarianActivity extends AppCompatActivity {
    private RecyclerView listVeterinarian;
    private ImageView btnBack;
    private ImageView btnSearch;
    private EditText search;
    private ListVererinarianAdapter vererinarianAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_veterinarian);
        listVeterinarian = findViewById(R.id.list_veterinarian);
        btnBack = findViewById(R.id.btn_back);
        btnSearch = findViewById(R.id.btn_search);
        search = findViewById(R.id.search);
        search.setVisibility(View.GONE);
        vererinarianAdapter = new ListVererinarianAdapter(getList(),ListVeterinarianActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListVeterinarianActivity.this, LinearLayoutManager.VERTICAL, false);
        listVeterinarian.setAdapter(vererinarianAdapter);
        listVeterinarian.setLayoutManager(layoutManager);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search.getVisibility() == View.GONE){
                    search.setVisibility(View.VISIBLE);
                }else{
                    search.setVisibility(View.GONE);
                }
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private List<Veterinatian> getList(){
        List<Veterinatian> veterinatians = new ArrayList<>();
        veterinatians.add(new Veterinatian(1L, "Dr.Lyly", "1234567","bs1@gmail.com","mổ",4.3f,3, "a", 500));
        veterinatians.add(new Veterinatian(1L, "Dr.Lyly", "1234567","bs1@gmail.com","mổ",4.3f,3, "a", 500));
        veterinatians.add(new Veterinatian(1L, "Dr.Lyly", "1234567","bs1@gmail.com","mổ",4.3f,3, "a", 500));
        veterinatians.add(new Veterinatian(1L, "Dr.Lyly", "1234567","bs1@gmail.com","mổ",4.3f,3, "a", 500));
        return  veterinatians;
    }
}