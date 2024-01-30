package com.project.petmanagement.petmanagement.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    Integer status;
    Object message;
}
