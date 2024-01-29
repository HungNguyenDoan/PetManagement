package com.project.petmanagement.petmanagement.payloads.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotEmpty(message = "Please enter username")
    String username;
    @NotEmpty(message = "Please enter password")
    String password;
}
