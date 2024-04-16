package com.project.petmanagement;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.project.petmanagement.services.StorageService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyApplication extends Application {
    private static StorageService storageService;
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        storageService = new StorageService(getApplicationContext());
//        ApiService.apiService.getAllPetUser().enqueue(new Callback<ListPetResponse>() {
//            @Override
//            public void onResponse(Call<ListPetResponse> call, Response<ListPetResponse> response) {
//                if(response.code() == 403){
//                    storageService.remove("token");
//                    storageService.remove("user");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListPetResponse> call, Throwable t) {
//                storageService.remove("token");
//                storageService.remove("user");
//            }
//        });

    }
    public static StorageService getStorageService(){
        return storageService;
    }
    private void saveImage(Bitmap bitmap, int stt){
        File dir = new File(getFilesDir(), "product_image");
        if(!dir.exists()){
            dir.mkdir();
        }
        if(bitmap != null){
            File file = new File(dir, "product_"+ stt+".jpg");
            OutputStream outputStream;
            try {
                outputStream = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
            Log.d("ddddddddddd", file.getAbsolutePath());
            try {
                outputStream.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
