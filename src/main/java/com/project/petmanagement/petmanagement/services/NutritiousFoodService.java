package com.project.petmanagement.petmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.NutritiousFoodRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor

@Service
public class NutritiousFoodService {
    private final NutritiousFoodRepository nutritiousFoodRepository;
    private final FoodTypeRepository foodTypeRepository;

    public List<NutritiousFood> getAllNutritiousFood() {
        return nutritiousFoodRepository.findAll();
    }

    public NutritiousFood getDetailNutritiousFood(Long id) {
        return nutritiousFoodRepository.getReferenceById(id);
    }

    public List<NutritiousFood> searchNutritiousFood(String key, Long food_id) {
        if (food_id != null && key != null) {
            FoodType foodType = foodTypeRepository.getReferenceById(food_id);
            return nutritiousFoodRepository.findByNutritionNameContainingAndFoodType(key, foodType);
        } else if (food_id != null) {
            FoodType foodType = foodTypeRepository.getReferenceById(food_id);
            return nutritiousFoodRepository.findByFoodType(foodType);
        } else {
            return nutritiousFoodRepository.findByNutritionNameContaining(key);
        }
    }
}
