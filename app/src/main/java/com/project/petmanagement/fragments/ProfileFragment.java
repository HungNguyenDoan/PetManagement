package com.project.petmanagement.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.MainActivity;
import com.project.petmanagement.activity.login.LoginActivity;
import com.project.petmanagement.activity.nutrition.NutritionActivity;
import com.project.petmanagement.activity.pet.ManagePetActivity;
import com.project.petmanagement.activity.shop.ShopActivity;
import com.project.petmanagement.activity.user.ChangePassword;
import com.project.petmanagement.activity.veterinarian.ListVeterinarianActivity;
import com.project.petmanagement.models.entity.User;
import com.project.petmanagement.payloads.requests.FCMToken;
import com.project.petmanagement.payloads.responses.Response;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

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
        CardView changePassword = view.findViewById(R.id.change_password_card_view);
        CardView deleteAccount = view.findViewById(R.id.delete_account_card_view);
        Button btnLogout = view.findViewById(R.id.btn_logout);
        TextView fullName = view.findViewById(R.id.full_name);
        TextView phoneNumber = view.findViewById(R.id.phone_number);
        ImageView avatar = view.findViewById(R.id.avatar);
        User user = storageService.getUser("user");
        if (user != null) {
            fullName.setText(user.getFullName());
            String phone = "+84 " + user.getPhoneNumber().substring(1, 4) + " xxx xxx";
            phoneNumber.setText(phone);
            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
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
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePassword.class);
                startActivity(intent);
            }
        });
        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(requireContext());
                alertDialog.setTitle("Thông báo")
                        .setMessage("Bạn có chắc chắn muốn xóa tài khoản")
                        .setPositiveButton("Có", (dialog, which) -> {
                            ApiService.apiService.deleteUser().enqueue(new Callback<Response>() {
                                @Override
                                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                                    if (response.isSuccessful()) {
                                        storageService.remove("user");
                                        storageService.remove("token");
                                        Intent intent = new Intent(requireContext(), LoginActivity.class);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<Response> call, Throwable t) {
                                    Toast.makeText(requireContext(), "Xóa tài khoản thất bại.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        })
                        .setNegativeButton("Không", (dialog, which) -> dialog.cancel())
                        .show();
            }
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