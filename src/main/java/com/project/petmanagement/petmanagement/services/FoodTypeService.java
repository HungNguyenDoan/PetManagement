package com.project.petmanagement.petmanagement.services;

import java.util.ArrayList;
import java.util.List;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodTypeService {
//    private final FoodTypeRepository foodTypeRepository;
//
//    public List<FoodType> getAllFoodTypes() {
//        return new ArrayList<>(foodTypeRepository.findAll());
//    }
//
//    public FoodType getFoodTypeDetails(Long foodTypeId) throws DataNotFoundException {
//        return foodTypeRepository.findById(foodTypeId).orElseThrow(() -> new DataNotFoundException("Can not find food type details with ID: " + foodTypeId));
//    }
}
