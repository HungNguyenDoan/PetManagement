package com.project.petmanagement.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.petmanagement.model.User;
import com.project.petmanagement.model.UserLogin;
import com.project.petmanagement.model.UserSignup;
import com.project.petmanagement.response.UserResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    String apiURLDeploy = "http://13.239.47.248/";
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setLenient().create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(apiURLDeploy)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @POST("auth/login")
    Call<UserResponse> login(@Body UserLogin userLogin);
    @POST("auth/register")
    Call<UserResponse> signup(@Body UserSignup userSignup);
}
