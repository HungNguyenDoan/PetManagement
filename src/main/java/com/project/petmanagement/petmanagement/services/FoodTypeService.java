package com.project.petmanagement.petmanagement.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodTypeService {
    private final FoodTypeRepository foodTypeRepository;

    public List<FoodType> getAllFoodType() {
        List<FoodType> foodTypes = new ArrayList<>();
        foodTypeRepository.findAll().forEach(foodTypes::add);
        return foodTypes;
    }
}
