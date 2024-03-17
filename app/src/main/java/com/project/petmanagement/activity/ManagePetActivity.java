package com.project.petmanagement.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.project.petmanagement.R;
import com.project.petmanagement.adapter.ManagePetRecyclerViewAdapter;
import com.project.petmanagement.model.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class ManagePetActivity extends AppCompatActivity {
    private RecyclerView petRecyclerView;
    private Button btnAddPet;
    private ImageView btnBack;
    private ImageView btnSearch;
    private EditText search;
    private ManagePetRecyclerViewAdapter managePetRecyclerViewAdapter;
    private List<Pet> pets = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pet);
        findViewById();
        search = findViewById(R.id.search);
        search.setVisibility(View.GONE);
        pets = getPetList();
        managePetRecyclerViewAdapter = new ManagePetRecyclerViewAdapter(this, pets);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        petRecyclerView.setLayoutManager(linearLayoutManager);
        petRecyclerView.setAdapter(managePetRecyclerViewAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(petRecyclerView);
        btnAddPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagePetActivity.this, AddNewPetActivity.class);
                startActivity(intent);
            }
        });
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
    private void findViewById(){
        petRecyclerView = findViewById(R.id.pet_manage_recyclerview);
        btnAddPet = findViewById(R.id.btn_add_pet);
        btnBack = findViewById(R.id.btn_back);
        btnSearch = findViewById(R.id.btn_search);

    }
    private List<Pet> getPetList(){
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("Pet 2", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 3", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 4", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 5", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 6", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 7", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 8", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 9", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 10", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 11", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 12", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 13", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 14", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 15", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 16", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 17", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 18", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 19", "mèo ai cập", "Male", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 10", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        pets.add(new Pet("Pet 11", "mèo ai cập", "Female", "1 năm 6 tháng", 50.0));
        return pets;
    }
    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getBindingAdapterPosition();
            if(direction == ItemTouchHelper.LEFT){
                pets.remove(position);
                Objects.requireNonNull(petRecyclerView.getAdapter()).notifyDataSetChanged();
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftActionIcon(R.drawable.baseline_delete_24)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(ManagePetActivity.this, R.color.red))
                    .addSwipeLeftLabel("Delete")
                    .setSwipeLeftLabelColor(ContextCompat.getColor(ManagePetActivity.this, R.color.white))
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}
