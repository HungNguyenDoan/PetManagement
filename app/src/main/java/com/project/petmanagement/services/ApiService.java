package com.project.petmanagement.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.model.Pet;
import com.project.petmanagement.payload.request.PetRequest;
import com.project.petmanagement.payload.request.UserLogin;
import com.project.petmanagement.payload.request.UserSignup;
import com.project.petmanagement.payload.response.FoodTypeResponse;
import com.project.petmanagement.payload.response.NutritionInfoResponse;
import com.project.petmanagement.payload.response.ListPetResponse;
import com.project.petmanagement.payload.response.PetResponse;
import com.project.petmanagement.payload.response.ListSpeciesResponse;
import com.project.petmanagement.payload.response.UserResponse;

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
//    String apiURLDeploy = "http://103.163.215.125/api/";
    String apiURLDeploy = "http://192.151.62.100:8080/";
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
    @GET("species/all")
    Call<ListSpeciesResponse> getSpecies();
    @GET("pet/getPet")
    Call<ListPetResponse> getAllPetUser();
    @POST("pet/addPet")
    Call<PetResponse> addPet(@Body PetRequest petRequest);
}
