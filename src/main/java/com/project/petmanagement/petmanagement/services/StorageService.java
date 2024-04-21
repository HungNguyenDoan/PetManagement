package com.project.petmanagement.petmanagement.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.StorageClient;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class StorageService {

    @Value("${app.bucket-name}")
    private String bucketName;

    public void uploadFile(String fileName, byte[] fileBytes) {
        StorageClient storageClient = StorageClient.getInstance(FirebaseApp.getInstance());
        InputStream inputStream = new ByteArrayInputStream(fileBytes);
        storageClient.bucket().create(fileName, inputStream, "application/octet-stream");
    }
}
