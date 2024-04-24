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
import com.project.petmanagement.activity.pet.ManagePetActivity;
import com.project.petmanagement.activity.shop.ShopActivity;
import com.project.petmanagement.activity.veterinarian.ListVeterinarianActivity;
import com.project.petmanagement.models.entity.User;
import com.project.petmanagement.services.StorageService;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
        String imageUrl = "https://storage.googleapis.com/mobile-server-firebase.appspot.com/f0f9c3bb-96af-4dc4-acbc-fca8ce3a646b_product_5.jpg?GoogleAccessId=firebase-adminsdk-6vprl@mobile-server-firebase.iam.gserviceaccount.com&Expires=1713802711&Signature=XY2Y93Cx4YCx2NHM4UD4MOS1jRydjQkXs5Xu0XSS85WV9owHNGUYxFPpkzJexeYAU2WjhkBrE8wnj4eoebA5Ci3qhmPcjUKVB26Ik%2BzsZ4vWOM2UAzHXWfcy8J2ZR1HEwrrwvS13ZKcSeo08oz14Y8iALI1GxunpNAoGYVgodjGX9aipu09jXktHVr856mfgaoYgoRlVTg%2BA7M38jkNVm0WhJFkuopILLsWuQmvLGUqVNJ2w1a8q2GO6H18eiNV4CLLx2I1AXnjuBAntl%2FbKakte7tYoaOepxfKaeJTvVbc9KHrNjRd%2BIpBznW5XWFRv7pL%2FhfaF2dPH7InSeyGHfA%3D%3D";
        Glide.with(this).load(imageUrl).into(avatar);
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
//                Intent intent = new Intent(getContext(), NutritionActivity.class);
//                startActivity(intent);
            }
        });
        vet.setOnClickListener(new View.OnClickListener() {
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