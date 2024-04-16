package com.project.petmanagement.petmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.services.NutritiousFoodService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("nutritioninfo")
public class NutritionInfoController {
    private final NutritiousFoodService nutritionInfoService;

    @GetMapping("all")
    public ResponseEntity<Object> getListNutritionInfo(@RequestParam(required = false) String key,
            @RequestParam(required = false) Long foodTypeId) {
        List<NutritiousFood> nutritionList = nutritionInfoService.searchNutritionInfo(key, foodTypeId);
        NutritionInfoResponse response = NutritionInfoResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get list successfully")
                .data(nutritionList)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
