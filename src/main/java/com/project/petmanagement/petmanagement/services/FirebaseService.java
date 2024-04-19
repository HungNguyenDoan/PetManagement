package com.project.petmanagement.petmanagement.services;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

@Service
public class FirebaseService {
    public String pushNotification(FcmNotification pnsRequest) {
        Message message = Message.builder()
                .setNotification(Notification.builder()
                    .setTitle(pnsRequest.getTitle())
                    .setBody(pnsRequest.getBody())
                    .build())
                .setToken(pnsRequest.getFcmToken())
                .build();

        String response = null;
        try {
            response = FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }
        return response;
    }
}
