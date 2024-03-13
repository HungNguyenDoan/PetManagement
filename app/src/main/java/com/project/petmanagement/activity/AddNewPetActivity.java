package com.project.petmanagement.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import com.project.petmanagement.R;

import java.util.Arrays;

public class AddNewPetActivity extends AppCompatActivity {

    private ImageView btnBack;
    private String[] species = {"Chó", "Mèo"};
    private String[] breed1 = {"Chó 1", "Chó2"};
    private String[] breed2 = {"Mèo 1", "Mèo 2"};
    private AutoCompleteTextView speciesView;
    private AutoCompleteTextView breedView;
    private ArrayAdapter<String> speciesAdapter;
    private ArrayAdapter<String> breedAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_pet);
        speciesView = findViewById(R.id.species);
        breedView = findViewById(R.id.breed);
        btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        speciesAdapter = new ArrayAdapter<>(this, R.layout.list_item_dropdown, species);
        speciesView.setAdapter(speciesAdapter);
        speciesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String speciesSelect = parent.getItemAtPosition(position).toString();
                if(speciesSelect.equals("Chó")){
                    breedAdapter = new ArrayAdapter<>(AddNewPetActivity.this, R.layout.list_item_dropdown, breed1);
                } else if (speciesSelect.equals("Mèo")) {
                    breedAdapter = new ArrayAdapter<>(AddNewPetActivity.this, R.layout.list_item_dropdown, breed2);
                }
                breedView.setAdapter(breedAdapter);
            }
        });
    }
}