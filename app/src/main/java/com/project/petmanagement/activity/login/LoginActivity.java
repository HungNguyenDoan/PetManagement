package com.project.petmanagement.activity.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.MainActivity;
import com.project.petmanagement.models.entity.User;
import com.project.petmanagement.payloads.requests.LoginRequest;
import com.project.petmanagement.payloads.responses.LoginResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;
import com.project.petmanagement.utils.DialogUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private TextView textSignup, textBack;
    private TextInputEditText inputPhoneNumber, inputPassword;
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
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void login(){
        if(validation()){
            String phoneNumber = inputPhoneNumber.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            ApiService.apiService.login(new LoginRequest(phoneNumber, password)).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful()){
                        LoginResponse userResponse = response.body();
                        storageService.setString("token", userResponse.getToken());
                        Log.d("ddddd", userResponse.getToken());
                        User user = userResponse.getData();
                        storageService.setUser("user",user);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }else{
                        DialogUtils.setUpDialog(LoginActivity.this,"Tài khoản hoặc mật khẩu của bạn không đúng");
                    }

                }
                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    DialogUtils.setUpDialog(LoginActivity.this,"Kiểm tra kết nối của bạn.");
                }
            });
        }
    }
    private boolean validation(){
        if(inputPhoneNumber.length()==0){
            inputPhoneNumber.setError("Bạn chưa điền username");
            return false;
        }
        if(inputPassword.length()==0){
            inputPassword.setError("Bạn chưa điền mật khẩu");
            return false;
        }
        return true;
    }
    private void findViewById(){
        textSignup = findViewById(R.id.text_signup);
        inputPhoneNumber = findViewById(R.id.phone_number);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        textBack = findViewById(R.id.text_back);
    }
    private void changeSignup(){
        textSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}