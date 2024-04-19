package com.project.petmanagement.petmanagement.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FcmToken {
    String fcmToken;
}
