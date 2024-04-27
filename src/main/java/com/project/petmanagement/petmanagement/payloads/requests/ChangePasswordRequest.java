package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.petmanagement.petmanagement.constraints.annotations.Changed;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequest {
    
    @NotNull(message = "Please enter old password")
    @Changed(message = "Old password is not match", condition = true)
    @JsonProperty("old_password")
    String oldPassword;

    @NotNull(message = "Please enter new password")
    @Changed(message = "New password must not be same with old password", condition = false)
    @JsonProperty("new_password")
    String newPassword;

    @NotNull(message = "Please re-enter new password")
    @JsonProperty("re_new_password")
    String renewPassword;

}
