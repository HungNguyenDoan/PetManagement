package com.project.petmanagement.activity.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.activity.MainActivity;
import com.project.petmanagement.models.entity.User;
import com.project.petmanagement.payloads.requests.RegisterRequest;
import com.project.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.payloads.responses.LoginResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;
import com.project.petmanagement.utils.DialogUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private TextView textLogin, textBack;
    private TextInputEditText fullName, dob, phoneNumber, password, rePassword, email, address;
    private Button btnSignup;
    private DatePickerDialog datePickerDialog;
    private final StorageService storageService = MyApplication.getStorageService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewById();
        changeLogin();
        customDob();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        textBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void findViewById(){
        textLogin = findViewById(R.id.text_login);
        btnSignup = findViewById(R.id.btn_signup);
        fullName = findViewById(R.id.full_name);
        dob = findViewById(R.id.dob);
        phoneNumber = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        rePassword = findViewById(R.id.confirm_password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        textBack = findViewById(R.id.text_back);
    }
    private void signUp(){
        if(validation()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            if(!password.getText().toString().equals(rePassword.getText().toString())){
                DialogUtils.setUpDialog(RegisterActivity.this, "Mật khẩu xác nhận không đúng.");
            }
            else{
                try {
                    SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    Date dob2 = sdf1.parse(dob.getText().toString());
                    String dob3 = sdf.format(dob2);
                    Date dob1 = sdf.parse(dob3);
                    RegisterRequest registerRequest = new RegisterRequest(fullName.getText().toString(),dob1, phoneNumber.getText().toString(), email.getText().toString(), address.getText().toString(), password.getText().toString());
                    ApiService.apiService.signup(registerRequest).enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            Log.d("ddddd", response.code()+"");
                            if(response.code() == 200){
                                LoginResponse loginResponse = response.body();
                                storageService.setString("token", loginResponse.getToken());
                                User user = loginResponse.getData();
                                storageService.setUser("user",user);
                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else if (response.code() == 400) {
                                Gson gson = new Gson();
                                ErrorResponse errorResponse = null;
                                try {
                                    errorResponse = gson.fromJson(response.errorBody().string(), ErrorResponse.class);
                                    String message = "";
                                    if(errorResponse.getMessage().getEmailError()!=null){
                                        message+= errorResponse.getMessage().getEmailError()+"\n";
                                    }
                                    if(errorResponse.getMessage().getPasswordError()!=null){
                                        message+= errorResponse.getMessage().getPasswordError()+"\n";
                                    }
                                    if(errorResponse.getMessage().getFullNameError()!=null){
                                        message+= errorResponse.getMessage().getFullNameError()+"\n";
                                    }
                                    if (errorResponse.getMessage().getPhoneNumberError() !=null){
                                        message+= errorResponse.getMessage().getPhoneNumberError() + "\n";
                                    }
                                    DialogUtils.setUpDialog(RegisterActivity.this, message);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "Kiểm tra lại kết nối mạng.", Toast.LENGTH_SHORT).show();
                            Log.d("dddd", t.getMessage());
                        }
                    });
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
    private boolean validation(){
        if(fullName.length()==0){
            fullName.setError("Tên không được để trống");
            return false;
        }
        if(dob.length() == 0){
            dob.setError("Ngày sinh không được để trống.");
            return false;
        }
        if(phoneNumber.length()==0){
            phoneNumber.setError("Số điện thoại không được để trống.");
            return false;
        }
        if(password.length()==0){
            password.setError("Mật khẩu không được để trống.");
            return false;
        }

        if(rePassword.length()==0){
            rePassword.setError("Xác nhận mật khẩu không dược để trống.");
            return false;
        }
        if(email.length()==0){
            email.setError("Email không được để trống.");
            return false;
        }
        return true;
    }
    private void customDob(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date = dayOfMonth+"/"+ month +"/"+year;
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        try {
                            Date date1 = sdf.parse(date);
                            String date2 = sdf.format(date1);
                            dob.setText(date2);
                            dob.setError(null);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }

    private void changeLogin(){
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}