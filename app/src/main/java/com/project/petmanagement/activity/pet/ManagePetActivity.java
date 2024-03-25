package com.project.petmanagement.activity.pet;

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
import com.project.petmanagement.payload.response.ListPetResponse;
import com.project.petmanagement.services.ApiService;

import java.util.List;
import java.util.Objects;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagePetActivity extends AppCompatActivity {
    private RecyclerView petRecyclerView;
    private Button btnAddPet;
    private ImageView btnBack;
    private ImageView btnSearch;
    private EditText search;
    private ManagePetRecyclerViewAdapter managePetRecyclerViewAdapter;
    private List<Pet> pets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pet);
        findViewById();
        processBtn();
        getPetList();
        search.setVisibility(View.GONE);
        //Adapter
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(petRecyclerView);
        // Tìm pet
        search();

    }
    private void findViewById(){
        petRecyclerView = findViewById(R.id.pet_manage_recyclerview);
        btnAddPet = findViewById(R.id.btn_add_pet);
        btnBack = findViewById(R.id.btn_back);
        btnSearch = findViewById(R.id.btn_search);
        search = findViewById(R.id.search);
    }
    private void search(){
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
    private void processBtn(){
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
    }
    private void getPetList(){
        ApiService.apiService.getAllPetUser().enqueue(new Callback<ListPetResponse>() {
            @Override
            public void onResponse(Call<ListPetResponse> call, Response<ListPetResponse> response) {
                if(response.isSuccessful()){
                    ListPetResponse petResponse = response.body();
                    if (petResponse != null) {
                        pets = petResponse.getData();
                        managePetRecyclerViewAdapter = new ManagePetRecyclerViewAdapter(ManagePetActivity.this, pets);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ManagePetActivity.this, RecyclerView.VERTICAL, false);
                        petRecyclerView.setLayoutManager(linearLayoutManager);
                        petRecyclerView.setAdapter(managePetRecyclerViewAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListPetResponse> call, Throwable t) {

            }
        });
    }
    // Xử lý xóa pet
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

    @Override
    protected void onResume() {
        super.onResume();
        getPetList();
    }
}
