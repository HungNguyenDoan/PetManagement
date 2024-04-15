package com.project.petmanagement.petmanagement.payloads.requests;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.petmanagement.petmanagement.constraints.Unique;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @JsonProperty("full_name")
    @NotEmpty(message = "Full name is not empty")
    String fullName;

    @JsonProperty("date_of_birth")
    @NotNull(message = "Date of birth is required.")
    Date dateOfBirth;

    @JsonProperty("phone_number")
    @NotEmpty(message = "Phone number is not empty")
    @Unique(fieldName = "phoneNumber", message = "Phone number has been registered", table = "User")
    @Size(max = 10, min = 10, message = "Phone number should be included 10 digits")
    String phoneNumber;

    @NotEmpty(message = "Email is not empty")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email is not in the right format")
    String email;

    String address;

    String avatar;

    @NotEmpty(message = "Password is not empty")
    String password;
}
