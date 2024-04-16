package com.project.petmanagement.petmanagement.services;

import java.util.List;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.Species;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.SpeciesRepository;
import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.repositories.NutritiousFoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NutritiousFoodService {
    private final NutritiousFoodRepository nutritiousFoodRepository;
    private final FoodTypeRepository foodTypeRepository;
    private final SpeciesRepository speciesRepository;

    public List<NutritiousFood> getAllNutritiousFood() {
        return nutritiousFoodRepository.findAll();
    }

    public NutritiousFood getNutritiousFoodDetails(Long nutritiousFoodId) throws DataNotFoundException {
        return nutritiousFoodRepository.findById(nutritiousFoodId).orElseThrow(() -> new DataNotFoundException("Can not find nutritious food with ID: " + nutritiousFoodId));
    }

    public List<NutritiousFood> getNutritiousFoodBySpecies(Long speciesId) throws Exception {
        Species existingSpecies = speciesRepository.findById(speciesId).orElseThrow(() -> new DataNotFoundException("Can not find species with ID: " + speciesId));
        return nutritiousFoodRepository.findBySpecies(existingSpecies);
    }

    public List<NutritiousFood> getNutritiousFoodByFoodType(Long foodTypeId) throws Exception {
        FoodType existingFoodType = foodTypeRepository.findById(foodTypeId).orElseThrow(() -> new DataNotFoundException("Can not find food type with ID: " + foodTypeId));
        return nutritiousFoodRepository.findByFoodType(existingFoodType);
    }

    public List<NutritiousFood> getNutritiousFoodBySpeciesAndFoodType(Long speciesId, Long foodTypeId) throws Exception {
        Species existingSpecies = speciesRepository.findById(speciesId).orElseThrow(() -> new DataNotFoundException("Can not find species with ID: " + speciesId));
        FoodType existingFoodType = foodTypeRepository.findById(foodTypeId).orElseThrow(() -> new DataNotFoundException("Can not find food type with ID: " + foodTypeId));
        return nutritiousFoodRepository.findBySpeciesAndFoodType(existingSpecies, existingFoodType);
    }

    public List<NutritiousFood> searchNutritiousFood(String keywords) {
        return nutritiousFoodRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrNutritionContainingIgnoreCaseOrIngredientContainingIgnoreCase(keywords, keywords, keywords, keywords);
    }
}
