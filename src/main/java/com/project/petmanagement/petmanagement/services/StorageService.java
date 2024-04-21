package com.project.petmanagement.petmanagement.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class StorageService {

    @Value("$bucket-name")
    private String bucketName;
    public void uploadFile(String fileName, byte[] fileBytes) {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        Bucket bucket = storage.get(bucketName);

        InputStream inputStream = new ByteArrayInputStream(fileBytes);

        bucket.create(fileName, inputStream, "application/octet-stream");
    }
}
