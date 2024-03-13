package com.project.petmanagement.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.model.User;
import com.project.petmanagement.model.UserLogin;
import com.project.petmanagement.model.UserSignup;
import com.project.petmanagement.response.FoodTypeResponse;
import com.project.petmanagement.response.NutritionInfoResponse;
import com.project.petmanagement.response.UserResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    String apiURLDeploy = "";
    StorageService storageService = MyApplication.getStorageService();
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd")
            .setLenient().create();
    Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            String authToken = "Bearer "+storageService.getString("token");
            builder.header("Authorization", authToken);
            return chain.proceed(builder.build());
        }
    };
    OkHttpClient.Builder okClient = new OkHttpClient.Builder().addInterceptor(interceptor);
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(apiURLDeploy)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okClient.build())
            .build()
            .create(ApiService.class);
    @POST("auth/login")
    Call<UserResponse> login(@Body UserLogin userLogin);
    @POST("auth/register")
    Call<UserResponse> signup(@Body UserSignup userSignup);

    @GET("foodtype/all")
    Call<FoodTypeResponse> getAllFoodType();
    @GET("nutritioninfo/all")
    Call<NutritionInfoResponse> getListNutritionInfo(@Query("key") String key, @Query("foodTypeId") Long foodTypeId);
}
