package com.project.petmanagement.petmanagement.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.payloads.responses.FoodTypeResponse;
import com.project.petmanagement.petmanagement.services.FoodTypeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/foodtype")
@RequiredArgsConstructor
public class FoodTypeController {

    private final FoodTypeService foodTypeService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllFoodType() {
        FoodTypeResponse response = FoodTypeResponse.builder()
                                        .status(HttpStatus.OK.value())
                                        .message("Get all food type success")
                                        .data(foodTypeService.getAllFoodType())
                                        .build();
        return new ResponseEntity<Object>(response,HttpStatus.OK);
    }
}
