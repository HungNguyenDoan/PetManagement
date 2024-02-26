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

    public List<NutritionInfo> searchNutritionInfo(String key, Long food_id) {
        if (food_id != null && key != null) {
            FoodType foodType = foodTypeRepository.getReferenceById(food_id);
            return nutritionInfoRepository.findByNutritionNameContainingAndFoodType(key, foodType);
        } else if (food_id != null) {
            FoodType foodType = foodTypeRepository.getReferenceById(food_id);
            return nutritionInfoRepository.findByFoodType(foodType);
        } else {
            return nutritionInfoRepository.findByNutritionNameContaining(key);
        }
    }
}
