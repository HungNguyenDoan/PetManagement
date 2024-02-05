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
        FoodType foodType1 = FoodType.builder().id(1L).typeName("test1").build();
        FoodType foodType2 = FoodType.builder().id(2L).typeName("test2").build();
        FoodType foodType3 = FoodType.builder().id(3L).typeName("test3").build();
        FoodType foodType4 = FoodType.builder().id(4L).typeName("test4").build();
        foodTypeRepository.saveAll(Arrays.asList(foodType1, foodType2, foodType3, foodType4));
    }
}
