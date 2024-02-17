package com.project.petmanagement.petmanagement.seeders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.FoodType;
import com.project.petmanagement.petmanagement.models.NutritionInfo;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.NutritionInfoRepository;

@Component
public class NutritionInfoSeeder {
    @Autowired
    private NutritionInfoRepository nutritionInfoRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public void seed() {
        FoodType food1 = foodTypeRepository.getReferenceById(1L);
        FoodType food2 = foodTypeRepository.getReferenceById(2L);
        FoodType food3 = foodTypeRepository.getReferenceById(3L);
        FoodType food4 = foodTypeRepository.getReferenceById(4L);
        FoodType food5 = foodTypeRepository.getReferenceById(5L);
        List<FoodType> listSeedFoodType = new ArrayList<>();
        listSeedFoodType.add(food1);
        listSeedFoodType.add(food2);
        listSeedFoodType.add(food3);
        listSeedFoodType.add(food4);
        listSeedFoodType.add(food5);
        Random generator = new Random();
        NutritionInfo nutri1 = NutritionInfo.builder().id(1L).nutritionName("Test 1").description("").build();
        nutri1.addFoodType(listSeedFoodType.get(generator.nextInt(5)));
        NutritionInfo nutri2 = NutritionInfo.builder().id(2L).nutritionName("").description("").build();
        nutri2.addFoodType(listSeedFoodType.get(generator.nextInt(5)));
        NutritionInfo nutri3 = NutritionInfo.builder().id(3L).nutritionName("").description("").build();
        nutri3.addFoodType(listSeedFoodType.get(generator.nextInt(5)));
        NutritionInfo nutri4 = NutritionInfo.builder().id(4L).nutritionName("").description("").build();
        nutri4.addFoodType(listSeedFoodType.get(generator.nextInt(5)));
        NutritionInfo nutri5 = NutritionInfo.builder().id(5L).nutritionName("").description("").build();
        nutri5.addFoodType(listSeedFoodType.get(generator.nextInt(5)));
        nutritionInfoRepository.saveAll(Arrays.asList(new NutritionInfo[] {nutri1, nutri2, nutri3, nutri4, nutri5}));
    }
}
