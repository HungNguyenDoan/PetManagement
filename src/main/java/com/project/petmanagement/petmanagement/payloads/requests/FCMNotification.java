package com.project.petmanagement.petmanagement.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FCMNotification {
    String FCMToken;
    String title;
    String body;
    Object data;
}
