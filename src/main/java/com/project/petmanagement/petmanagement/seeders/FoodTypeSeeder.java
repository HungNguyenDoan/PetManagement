package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.FoodType;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;

@Component
public class FoodTypeSeeder {
    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public void seed() {
        FoodType foodType1 = FoodType.builder().id(1L).typeName("Dry Kibble").build();
        FoodType foodType2 = FoodType.builder().id(2L).typeName("Canned Wet Food").build();
        FoodType foodType3 = FoodType.builder().id(3L).typeName("Raw Food").build();
        FoodType foodType4 = FoodType.builder().id(4L).typeName("Freeze-Dried Food").build();
        FoodType foodType5 = FoodType.builder().id(5L).typeName("Veterinary Diets").build();
        FoodType foodType6 = FoodType.builder().id(6L).typeName("Grain-Free Food").build();
        FoodType foodType7 = FoodType.builder().id(7L).typeName("Organic Pet Food").build();
        FoodType foodType8 = FoodType.builder().id(8L).typeName("Prescription Diets").build();
        FoodType foodType9 = FoodType.builder().id(9L).typeName("Dental Chews").build();
        FoodType foodType10 = FoodType.builder().id(10L).typeName("Semi-Moist Treats").build();
        foodTypeRepository.saveAll(Arrays.asList(foodType1, foodType2, foodType3, foodType4, foodType5, foodType6,
                foodType7, foodType8, foodType9, foodType10));
    }
}
