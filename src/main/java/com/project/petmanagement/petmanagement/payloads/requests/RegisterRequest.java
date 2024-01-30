package com.project.petmanagement.petmanagement.payloads.requests;

import java.util.Date;

import com.project.petmanagement.petmanagement.constraints.Unique;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequest {
    @NotEmpty(message = "Full name is not empty")
    String fullName;
    @NotEmpty(message = "Phone number is not empty")
    @Unique(fieldName = "phonenumber", message = "Phone number has been registered", table = "User")
    @Size(max = 10, min = 10, message = "Phone number should be included 10 digits")
    String phonenumber;
    @NotEmpty(message = "Password is not empty")
    String password;
    @NotEmpty(message = "Email is not empty")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email is not in the right format")
    String email;
    Date dob;
    String address;
}
