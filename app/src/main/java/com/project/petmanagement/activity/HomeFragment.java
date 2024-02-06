package com.project.petmanagement.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.petmanagement.R;
import com.project.petmanagement.adapter.HomePetRecycleViewAdapter;
import com.project.petmanagement.adapter.SliderAdapter;
import com.project.petmanagement.model.Image;
import com.project.petmanagement.model.Pet;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {
    private RecyclerView petRecyclerView;
    private ViewPager2 sliderViewpager2;
    private CircleIndicator3 circleIndicator3;
    private List<Image> imageSliders;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if(sliderViewpager2.getCurrentItem()== imageSliders.size()-1){
                sliderViewpager2.setCurrentItem(0);
            }else{
                sliderViewpager2.setCurrentItem(sliderViewpager2.getCurrentItem()+1);
            }
        }
    };
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViewById(view);
        //Slider
        setSlider();
        //My pet
        List<Pet> pets = new ArrayList<>();
        pets.add(new Pet("pet1", "tay tang"));
        pets.add(new Pet("pet2", "tay tang"));
        pets.add(new Pet("pet3", "tay tang"));
        pets.add(new Pet("pet4", "tay tang"));
        HomePetRecycleViewAdapter petAdapter = new HomePetRecycleViewAdapter();
        petAdapter.setDate(pets);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        petRecyclerView.setAdapter(petAdapter);
        petRecyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }


    private void findViewById(View view){
        petRecyclerView = view.findViewById(R.id.rc_pet);
        sliderViewpager2 = view.findViewById(R.id.slider_viewpager2);
        circleIndicator3 = view.findViewById(R.id.circle_indicator);
    }
    private List<Image> getListImage(){
        List<Image> images = new ArrayList<>();
        images.add(new Image(R.drawable.backgroud_login));
        images.add(new Image(R.drawable.slider1));
        images.add(new Image(R.drawable.slider2));
        return images;
    }
    private void setSlider(){
        imageSliders = getListImage();
        SliderAdapter sliderAdapter = new SliderAdapter(imageSliders);
        sliderViewpager2.setAdapter(sliderAdapter);
        circleIndicator3.setViewPager(sliderViewpager2);
        sliderViewpager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 3000);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable,3000);
    }
}