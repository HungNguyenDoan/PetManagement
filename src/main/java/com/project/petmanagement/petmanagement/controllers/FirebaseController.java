package com.project.petmanagement.petmanagement.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.StorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/fcm")
@RequiredArgsConstructor
public class FirebaseController {

    private final StorageService service;

    @PostMapping("/add")
    public ResponseEntity<Object> addFileToFirebase(@RequestBody MultipartFile file) {
        try {
            byte[] fileByte = file.getBytes();
            String fileName = file.getOriginalFilename();
            service.uploadFile(fileName, fileByte);
            DataResponse response = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("success")
                    .data(null)
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse response = ErrorResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }
}
