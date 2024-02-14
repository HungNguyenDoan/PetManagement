package com.project.petmanagement.petmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.models.NutritionInfo;
import com.project.petmanagement.petmanagement.payloads.responses.NutritionInfoResponse;
import com.project.petmanagement.petmanagement.services.NutritionInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NutritionInfoController {
    private final NutritionInfoService nutritionInfoService;

    @GetMapping
    public ResponseEntity<Object> getListNutritionInfo(@RequestParam String key, @RequestParam Long foodTypeId) {
        List<NutritionInfo> nutritionList = nutritionInfoService.getAllNutritionInfoByFoodType(foodTypeId);
        NutritionInfoResponse response = NutritionInfoResponse.builder()
                                            .status(HttpStatus.OK.value())
                                            .message("Get list successfully")
                                            .data(nutritionList)
                                            .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
