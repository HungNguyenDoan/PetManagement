package com.project.petmanagement.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.payloads.requests.LoginRequest;
import com.project.petmanagement.payloads.requests.RegisterRequest;
import com.project.petmanagement.payloads.responses.CartResponse;
import com.project.petmanagement.payloads.responses.ListCategoryResponse;
import com.project.petmanagement.payloads.responses.ListProductResponse;
import com.project.petmanagement.payloads.responses.LoginResponse;

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
    //server
//    String BASE_URL = "http://103.163.215.125/api/";
    //local
    String BASE_URL = "http://192.151.62.100:8080/";
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
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okClient.build())
            .build()
            .create(ApiService.class);
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @POST("auth/register")
    Call<LoginResponse> signup(@Body RegisterRequest registerRequest);

//    @GET("foodtype/all")
//    Call<FoodTypeResponse> getAllFoodType();
//    @GET("nutritioninfo/all")
//    Call<NutritionInfoResponse> getListNutritionInfo(@Query("key") String key, @Query("foodTypeId") Long foodTypeId);
//    @GET("species/all")
//    Call<ListSpeciesResponse> getSpecies();
//    @GET("pet/getPet")
//    Call<ListPetResponse> getAllPetUser();
//    @POST("pet/addPet")
//    Call<PetResponse> addPet(@Body PetRequest petRequest);
//    @GET("pet/{id}")
//    Call<PetResponse> getPetDetail(@Path("id") Long id);
//    @POST("pet/updatePet/{id}")
//    Call<PetResponse> updatePet(@Body PetRequest petRequest, @Path("id") Long id);
//    @POST("pet/deletePet/{petId}")
//    Call<PetResponse> deletePet(@Path("petId") Long petId);
    @GET("categories/")
    Call<ListCategoryResponse> getAllCategory();
    @GET("products/")
    Call<ListProductResponse> getAllProduct();
    @POST("carts/add")
    Call<CartResponse> addToCart(@Query("idProduct") Long idProduct, @Query("quantity") Integer quantity);
    @GET("carts/users/")
    Call<CartResponse> getCart();
}
