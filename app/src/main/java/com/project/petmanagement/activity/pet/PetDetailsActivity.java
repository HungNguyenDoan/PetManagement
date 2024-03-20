package com.project.petmanagement.activity.pet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.project.petmanagement.R;
import com.project.petmanagement.adapter.PetDetailsViewPager2Adapter;

public class PetDetailsActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ImageView btnBack, btnUpdatePet;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details);
        findViewById();
        PetDetailsViewPager2Adapter adapter = new PetDetailsViewPager2Adapter(this);
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Thông tin");
                    break;
                default:
                    tab.setText("Hoạt động");
                    break;
            }
        }).attach();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnUpdatePet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetDetailsActivity.this, UpdatePetActivity.class);
                startActivity(intent);
            }
        });
    }
    private void findViewById(){
        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager_2);
        toolbar = findViewById(R.id.tool_bar);
        btnBack = findViewById(R.id.btn_back);
        btnUpdatePet = findViewById(R.id.btn_update_pet);
    }

}