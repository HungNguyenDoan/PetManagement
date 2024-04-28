package com.project.petmanagement.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
//import com.project.petmanagement.activity.veterinarian.ListVeterinarianActivity;
import com.project.petmanagement.activity.login.LoginActivity;
//import com.project.petmanagement.activity.NutritionDetailsActivity;
import com.project.petmanagement.activity.MainActivity;
//import com.project.petmanagement.activity.pet.ManagePetActivity;
//import com.project.petmanagement.activity.nutrition.NutritionActivity;
import com.project.petmanagement.activity.nutrition.NutritionActivity;
import com.project.petmanagement.activity.pet.ManagePetActivity;
import com.project.petmanagement.activity.shop.ShopActivity;
import com.project.petmanagement.activity.veterinarian.ListVeterinarianActivity;
import com.project.petmanagement.models.entity.User;
import com.project.petmanagement.payloads.requests.FCMToken;
import com.project.petmanagement.payloads.responses.Response;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

import java.io.File;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;

public class ProfileFragment extends Fragment {
    private final StorageService storageService = MyApplication.getStorageService();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnLogin = view.findViewById(R.id.logout_btn);
        CardView pet = view.findViewById(R.id.pet_card_view);
        CardView nutrition = view.findViewById(R.id.nutrition_card_view);
        CardView vet = view.findViewById(R.id.veterinarian);
        CardView shop = view.findViewById(R.id.shop);
        Button btnLogout = view.findViewById(R.id.btn_logout);
        TextView fullName = view.findViewById(R.id.full_name);
        TextView phoneNumber = view.findViewById(R.id.phone_number);
        ImageView avatar = view.findViewById(R.id.avatar);
        User user = storageService.getUser("user");
        if(user != null){
            fullName.setText(user.getFullName());
            String phone = "+84 " + user.getPhoneNumber().substring(1,4)+" xxx xxx";
            phoneNumber.setText(phone);
            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        }else{
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);
        }
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
        });
        nutrition.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), NutritionActivity.class);
            startActivity(intent);
        });
        vet.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ListVeterinarianActivity.class);
            startActivity(intent);
        });
        shop.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ShopActivity.class);
            startActivity(intent);
        });
        pet.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), ManagePetActivity.class);
            startActivity(intent);
        });
        btnLogout.setOnClickListener(v -> {
            storageService.remove("user");
            storageService.remove("token");
            FCMToken fcmToken = new FCMToken("");
            ApiService.apiService.setFcmToken(fcmToken).enqueue(new Callback<com.project.petmanagement.payloads.responses.Response>() {
                @Override
                public void onResponse(retrofit2.Call<com.project.petmanagement.payloads.responses.Response> call1, retrofit2.Response<Response> response1) {
                    if (response1.isSuccessful()) {
                        Toast.makeText(requireActivity(), "set token is successful.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Response> call1, Throwable t) {
                    Toast.makeText(requireActivity(), "set token is failed.", Toast.LENGTH_SHORT).show();
                }
            });
            Intent intent = new Intent(requireContext(), MainActivity.class);
            startActivity(intent);
        });
    }

}