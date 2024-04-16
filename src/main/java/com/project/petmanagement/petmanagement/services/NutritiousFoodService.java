package com.project.petmanagement.petmanagement.services;

import java.util.List;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.NutritiousFoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NutritiousFoodService {
    private final NutritiousFoodRepository nutritiousFoodRepository;
    private final FoodTypeRepository foodTypeRepository;

    public List<NutritiousFood> getAllNutritiousFood() {
        return nutritiousFoodRepository.findAll();
    }

    public NutritiousFood getNutritiousFoodDetails(Long nutritiousFoodId) throws DataNotFoundException {
        return nutritiousFoodRepository.findById(nutritiousFoodId).orElseThrow(() -> new DataNotFoundException("Can not find nutritious food with ID: " + nutritiousFoodId));
    }

//    public List<NutritiousFood> searchNutritiousFood(String key, Long food_id) {
//        if (food_id != null && key != null) {
//            FoodType foodType = foodTypeRepository.getReferenceById(food_id);
//            return nutritiousFoodRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrNutritionContainingIgnoreCaseOrIngredientContainingIgnoreCase(key, foodType);
//        } else if (food_id != null) {
//            FoodType foodType = foodTypeRepository.getReferenceById(food_id);
//            return nutritiousFoodRepository.findByFoodType(foodType);
//        } else {
//            return nutritiousFoodRepository.findByNutritionNameContaining(key);
//        }
//    }
}
