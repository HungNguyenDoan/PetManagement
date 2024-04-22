package com.project.petmanagement.petmanagement.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FCMNotification {
    String fcmToken;
    String title;
    String body;
    Object data;
}
