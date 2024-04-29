package com.project.petmanagement.activity;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.project.petmanagement.R;
import com.project.petmanagement.adapters.ViewPagerAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager2;
    private  BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager2 = findViewById(R.id.view_pager_2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager2.setAdapter(viewPagerAdapter);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_library).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_community).setChecked(true);
                        break;
                    case  3:
                        bottomNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    viewPager2.setCurrentItem(0);
                } else if (item.getItemId() == R.id.navigation_library) {
                    viewPager2.setCurrentItem(1);
                } else if(item.getItemId() == R.id.navigation_community){
                    viewPager2.setCurrentItem(2);
                } else{
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
        String fragmentZero = getIntent().getStringExtra("fragmentIndex");
        if(fragmentZero!=null){
            viewPager2.setCurrentItem(0);
            bottomNavigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
        }
    }

}