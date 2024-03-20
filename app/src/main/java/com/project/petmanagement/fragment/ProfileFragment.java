package com.project.petmanagement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.veterinarian.ListVeterinarianActivity;
import com.project.petmanagement.activity.login.LoginActivity;
//import com.project.petmanagement.activity.NutritionDetailsActivity;
import com.project.petmanagement.activity.MainActivity;
import com.project.petmanagement.activity.pet.ManagePetActivity;
import com.project.petmanagement.activity.nutrition.NutritionActivity;
import com.project.petmanagement.activity.shop.ShopActivity;
import com.project.petmanagement.model.User;
import com.project.petmanagement.services.StorageService;

public class ProfileFragment extends Fragment {
    private CardView pet, nutrition, veterinatian, shop;
    private Button btnLogin, btnLogout;
    private StorageService storageService = MyApplication.getStorageService();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin = view.findViewById(R.id.logout_btn);
        pet = view.findViewById(R.id.pet_card_view);
        nutrition = view.findViewById(R.id.nutrition_card_view);
        veterinatian = view.findViewById(R.id.veterinarian);
        shop = view.findViewById(R.id.shop);
        btnLogout = view.findViewById(R.id.btn_logout);
        User user = storageService.getUser("user");
        if(user != null){
            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        }else{
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NutritionActivity.class);
                startActivity(intent);
            }
        });
        veterinatian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListVeterinarianActivity.class);
                startActivity(intent);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ShopActivity.class);
                startActivity(intent);
            }
        });
        pet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ManagePetActivity.class);
                startActivity(intent);
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageService.remove("user");
                storageService.remove("token");
                Intent intent = new Intent(requireContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

}