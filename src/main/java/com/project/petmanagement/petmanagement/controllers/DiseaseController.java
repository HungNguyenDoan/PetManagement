package com.project.petmanagement.petmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.models.entity.Disease;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.DiseaseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/disease")
@RequiredArgsConstructor
public class DiseaseController {
    private final DiseaseService service;

    @GetMapping("/all")
    public ResponseEntity<Object> getAll() {
        List<Disease> diseases = service.getAll();
        if (diseases.isEmpty()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(500)
                    .message("There is no disease food in database.")
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        DataResponse dataResponse = DataResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get all diseases successfully")
                .data(diseases)
                .build();
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetail(@PathVariable Long id) {
        try {
            Disease disease = service.getDetail(id);
            DataResponse dataResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Get disease details successfully")
                    .data(disease)
                    .build();
            return new ResponseEntity<>(dataResponse, HttpStatus.OK);

        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
