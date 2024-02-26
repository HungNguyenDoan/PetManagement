package com.project.petmanagement.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.model.User;
import com.project.petmanagement.model.UserLogin;
import com.project.petmanagement.response.UserResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView textSignup;
    private EditText inputPhoneNumber, inputPassword;
    private Button btnLogin;
    private StorageService storageService = MyApplication.getStorageService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById();
        changeSignup();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }
    private void login(){
        String phoneNumber = inputPhoneNumber.getText().toString();
        String password = inputPassword.getText().toString();
        ApiService.apiService.login(new UserLogin(phoneNumber, password)).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if(userResponse!=null){
                    storageService.setString("token", userResponse.getToken());
                    User user = userResponse.getData();
                    storageService.setUser("user",user);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    setUpDialog("Tài khoản hoặc mật khẩu của bạn không đúng");
                }

            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                setUpDialog("Server error");
            }
        });
    }
    private void findViewById(){
        textSignup = findViewById(R.id.text_signup);
        inputPhoneNumber = findViewById(R.id.phone_number);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
    }
    private void changeSignup(){
        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    private  void setUpDialog(String message){
        AlertDialog.Builder arlertDialog = new AlertDialog.Builder(LoginActivity.this);
        arlertDialog.setTitle("Thông báo")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }


}