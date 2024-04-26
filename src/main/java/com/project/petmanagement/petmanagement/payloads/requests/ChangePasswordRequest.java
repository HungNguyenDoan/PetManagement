package com.project.petmanagement.petmanagement.payloads.requests;

import com.project.petmanagement.petmanagement.constraints.annotations.Changed;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangePasswordRequest {
    
    @NotNull(message = "Please enter old password")
    @Changed(message = "Old password is not match", condition = true)
    String old_password;

    @NotNull(message = "Please enter new password")
    @Changed(message = "New password must not be same with old password", condition = false)
    String new_password;

    @NotNull(message = "Please re-enter new password")
    String re_new_password;

}
