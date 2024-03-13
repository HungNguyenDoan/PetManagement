package com.project.petmanagement.activity;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.project.petmanagement.R;
import com.project.petmanagement.adapter.ListVererinarianAdapter;
import com.project.petmanagement.model.Veterinatian;

import java.util.ArrayList;
import java.util.List;

public class ListVeterinarianActivity extends AppCompatActivity {
    private RecyclerView listVeterinarian;
    private ListVererinarianAdapter vererinarianAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_veterinarian);
        listVeterinarian = findViewById(R.id.list_veterinarian);
        vererinarianAdapter = new ListVererinarianAdapter(getList(),ListVeterinarianActivity.this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListVeterinarianActivity.this, LinearLayoutManager.VERTICAL, false);
        listVeterinarian.setAdapter(vererinarianAdapter);
        listVeterinarian.setLayoutManager(layoutManager);
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