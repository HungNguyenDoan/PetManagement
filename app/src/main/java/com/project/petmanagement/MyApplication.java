package com.project.petmanagement;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.project.petmanagement.payload.response.ListPetResponse;
import com.project.petmanagement.services.ApiService;
import com.project.petmanagement.services.StorageService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyApplication extends Application {
    private static StorageService storageService;
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        storageService = new StorageService(getApplicationContext());
        ApiService.apiService.getAllPetUser().enqueue(new Callback<ListPetResponse>() {
            @Override
            public void onResponse(Call<ListPetResponse> call, Response<ListPetResponse> response) {
                if(response.code() == 403){
                    storageService.remove("token");
                    storageService.remove("user");
                }
            }

            @Override
            public void onFailure(Call<ListPetResponse> call, Throwable t) {
                storageService.remove("token");
                storageService.remove("user");
            }
        });
    }
    public static StorageService getStorageService(){
        return storageService;
    }
}
