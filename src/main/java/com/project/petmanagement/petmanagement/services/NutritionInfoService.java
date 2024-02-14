package com.project.petmanagement.petmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.FoodType;
import com.project.petmanagement.petmanagement.models.NutritionInfo;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.NutritionInfoRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class NutritionInfoService {
    private final NutritionInfoRepository nutritionInfoRepository;
    private final FoodTypeRepository foodTypeRepository;

    public List<NutritionInfo> getAllNutritionInfo() {
        return nutritionInfoRepository.findAll();
    }

    public NutritionInfo getDetailNutritionInfo(Long id) {
        return nutritionInfoRepository.getReferenceById(id);
    }

    public List<NutritionInfo> getAllNutritionInfoByFoodType(Long foodTypeId) {
        // FoodType foodType = foodTypeRepository.getReferenceById(foodTypeId);
        return nutritionInfoRepository.findByListFoodTypesId(foodTypeId);
    }
}
