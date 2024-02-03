package com.project.petmanagement.activity;

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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.R;
import com.project.petmanagement.model.User;
import com.project.petmanagement.model.UserSignup;
import com.project.petmanagement.response.ErrorResponse;
import com.project.petmanagement.response.UserResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private TextView textLogin;
    private EditText fullName, dob, phoneNumber, password, rePassword, email, address;
    private Button btnSignup;
    private DatePickerDialog datePickerDialog;
    private StorageService storageService = MyApplication.getStorageService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        findViewById();
        changeLogin();
        customDob();
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
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
    }
    private void signUp(){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if(dob.getText().toString().isEmpty()){
            setUpDialog("ngay sinh không được để trống");
        }else if(!password.getText().toString().equals(rePassword.getText().toString())){
            setUpDialog("Xác nhận mật khẩu không đúng");
        }
        else{
            try {
                Date dob1 = sdf.parse(dob.getText().toString());
                UserSignup userSignup = new UserSignup(fullName.getText().toString(),password.getText().toString(),phoneNumber.getText().toString(),email.getText().toString(),address.getText().toString(),dob1);
                ApiService.apiService.signup(userSignup).enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if(response.code() == 200){
                            UserResponse userResponse = response.body();
                            storageService.setString("token", userResponse.getToken());
                            User user = userResponse.getData();
                            storageService.setUser("user",user);
                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
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
                                setUpDialog(message);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(SignupActivity.this, "api error", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void customDob(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog = new DatePickerDialog(SignupActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        dob.setText(year+"-"+(month+1)+"-"+dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
    }
    private  void setUpDialog(String message){
        AlertDialog.Builder arlertDialog = new AlertDialog.Builder(SignupActivity.this);
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
    private void changeLogin(){
        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}