package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.NutritiousFoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.services.FoodTypeService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/food_types")
@RequiredArgsConstructor
public class FoodTypeController {
    private final FoodTypeService foodTypeService;
    private final NutritiousFoodService nutritiousFoodService;

    @GetMapping("/")
    public ResponseEntity<Object> getAllFoodTypes() {
        List<FoodType> foodTypes = foodTypeService.getAllFoodTypes();
        if (foodTypes.isEmpty()) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(500)
                    .message("There is no food types in database.")
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        DataResponse dataResponse = DataResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get all food types successfully")
                .data(foodTypes)
                .build();
        return new ResponseEntity<>(dataResponse, HttpStatus.OK);
    }
}
