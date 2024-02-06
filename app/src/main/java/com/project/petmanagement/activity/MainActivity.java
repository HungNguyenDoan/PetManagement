package com.project.petmanagement.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.petmanagement.R;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private PetFragment petFragment = new PetFragment();
    private CommunityFragment communityFragment = new CommunityFragment();
    private MyPageFragment myPageFragment = new MyPageFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.menu_home){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                    return true;
                }else if(item.getItemId() == R.id.menu_pet){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, petFragment).commit();
                    return true;
                }else if(item.getItemId() == R.id.menu_community){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, communityFragment).commit();
                    return true;
                }else if(item.getItemId() == R.id.menu_my_page){
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, myPageFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
    private void findViewById(){
        bottomNavigationView = findViewById(R.id.bottom_navigation);
    }
}