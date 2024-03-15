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

import com.project.petmanagement.R;
import com.project.petmanagement.activity.ListVeterinarianActivity;
import com.project.petmanagement.activity.LoginActivity;
//import com.project.petmanagement.activity.NutritionDetailsActivity;
import com.project.petmanagement.activity.ManagePetActivity;
import com.project.petmanagement.activity.NutritionActivity;
import com.project.petmanagement.activity.ShopActivity;

public class ProfileFragment extends Fragment {
    private CardView pet, device, veterinatian, shop;
    private Button btnLogin;
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
        device = view.findViewById(R.id.device_card_view);
        veterinatian = view.findViewById(R.id.veterinarian);
        shop = view.findViewById(R.id.shop);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        device.setOnClickListener(new View.OnClickListener() {
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
    }

}