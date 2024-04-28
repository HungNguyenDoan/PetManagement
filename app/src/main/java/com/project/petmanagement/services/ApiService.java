package com.project.petmanagement.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.project.petmanagement.MyApplication;
import com.project.petmanagement.payloads.requests.FCMToken;
import com.project.petmanagement.payloads.requests.HealRecordRequest;
import com.project.petmanagement.payloads.requests.LoginRequest;
import com.project.petmanagement.payloads.requests.MedicalDocumentRequest;
import com.project.petmanagement.payloads.requests.OrderRequest;
import com.project.petmanagement.payloads.requests.PetRequest;
import com.project.petmanagement.payloads.requests.RegisterRequest;
import com.project.petmanagement.payloads.responses.CartResponse;
import com.project.petmanagement.payloads.responses.HealRecordResponse;
import com.project.petmanagement.payloads.responses.ListCategoryResponse;
import com.project.petmanagement.payloads.responses.ListDiseaseResponse;
import com.project.petmanagement.payloads.responses.ListFoodTypeResponse;
import com.project.petmanagement.payloads.responses.ListMedicalResponse;
import com.project.petmanagement.payloads.responses.ListNutritiousFoodResponse;
import com.project.petmanagement.payloads.responses.ListOrderResponse;
import com.project.petmanagement.payloads.responses.ListPetResponse;
import com.project.petmanagement.payloads.responses.ListProductResponse;
import com.project.petmanagement.payloads.responses.ListSpeciesResponse;
import com.project.petmanagement.payloads.responses.ListVaccineNotification;
import com.project.petmanagement.payloads.responses.LoginResponse;
import com.project.petmanagement.payloads.responses.MedicalDocumentResponse;
import com.project.petmanagement.payloads.responses.OrderResponse;
import com.project.petmanagement.payloads.responses.ListVetResponse;
import com.project.petmanagement.payloads.responses.PetResponse;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    //server
    String BASE_URL = "http://103.163.215.125/api/";
    //local
//    String BASE_URL = "http://192.151.62.105:8080/";
    StorageService storageService = MyApplication.getStorageService();
    Gson gson = new GsonBuilder()
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

    @GET("food_types/all")
    Call<ListFoodTypeResponse> getAllFoodType();
    @GET("nutritious_food/all")
    Call<ListNutritiousFoodResponse> getAllNutritious();
    @GET("species/all")
    Call<ListSpeciesResponse> getSpecies();
    @GET("diseases/all")
    Call<ListDiseaseResponse> getDiseases();
    @GET("pets/users")
    Call<ListPetResponse> getAllPetUser();
    @POST("pets/add")
    Call<PetResponse> addPet(@Body PetRequest petRequest);
    @GET("pets/{id}")
    Call<PetResponse> getPetDetail(@Path("id") Long id);
    @PUT("pets/{id}")
    Call<PetResponse> updatePet(@Body PetRequest petRequest, @Path("id") Long id);
    @DELETE("pets/{id}")
    Call<PetResponse> deletePet(@Path("id") Long petId);
    @GET("categories/all")
    Call<ListCategoryResponse> getAllCategory();
    @GET("products/all")
    Call<ListProductResponse> getAllProduct();
    @POST("carts/add")
    Call<CartResponse> addToCart(@Query("product_id") Long idProduct, @Query("quantity") Integer quantity);
    @GET("carts/users")
    Call<CartResponse> getCart();
    @PUT("carts/update")
    Call<CartResponse> updateCart(@Query("item_id") Long idItem, @Query("quantity") Integer quantity);
    @DELETE("carts/delete/cart_items/{id}")
    Call<CartResponse> deleteCartItem(@Path("id") Long idItem);
    @POST("orders/create")
    Call<OrderResponse> createOrder(@Body OrderRequest orderRequest);
    @GET("orders/users")
    Call<ListOrderResponse> getOrderUser();
    @PUT("orders/cancel/{id}")
    Call<OrderResponse> cancelOrder(@Path("id") Long idOrder);
    @GET("vets/all")
    Call<ListVetResponse> getAllVet();
    @GET("vets/search")
    Call<ListVetResponse> searchVet(@Query("keywords") String keywords);
    @Multipart
    @POST("medical_documents/")
    Call<MedicalDocumentResponse> addMedicalDocument(
            @Part("title") RequestBody title,
            @Part("note") RequestBody note,
            @Part("petId") RequestBody petId,
            @Part MultipartBody.Part file
    );
    @GET("medical_documents/pets/{id}")
    Call<ListMedicalResponse> getMedicalDocumentByPet(@Path("id") Long petId);
    @GET("medical_documents/{id}")
    Call<MedicalDocumentResponse> getMedicalDocumentByid(@Path("id") Long medicalId);
    @Multipart
    @PUT("medical_documents/{id}")
    Call<MedicalDocumentResponse> updateMedicalDocument(
            @Path("id") Long id,
            @Part("title") RequestBody title,
            @Part("note") RequestBody note,
            @Part("petId") RequestBody petId,
            @Part MultipartBody.Part file
            );
    @DELETE("medical_documents/{id}")
    Call<com.project.petmanagement.payloads.responses.Response> deleteMedicalDocument(@Path("id") Long medicalId);
    @POST("auth/fcm")
    Call<com.project.petmanagement.payloads.responses.Response> setFcmToken(@Body FCMToken fcmToken);
    @POST("health_records/add")
    Call<HealRecordResponse> addHealthRecord(@Body HealRecordRequest healRecordRequest);
    @GET("vaccination_notification/all")
    Call<ListVaccineNotification> getVaccineNotificationByUser();
}
