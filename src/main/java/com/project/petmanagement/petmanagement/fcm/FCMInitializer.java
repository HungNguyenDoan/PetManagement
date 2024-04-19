package com.project.petmanagement.petmanagement.fcm;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FCMInitializer {
    @Value("$app.firebase-config")
    private String fcmConfig;

    private FirebaseApp firebaseApp;

    public void initialize() {
        try {
            FirebaseOptions options =  FirebaseOptions.builder()
                    .setCredentials(
                            GoogleCredentials.fromStream(new ClassPathResource(fcmConfig).getInputStream()))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                firebaseApp = FirebaseApp.initializeApp(options);
            } else {
                firebaseApp = FirebaseApp.getInstance();
            }
        } catch (IOException e) {
            // logger.error("Create FirebaseApp Error", e);
        }
    }
}
