package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.NutritionInfo;
import com.project.petmanagement.petmanagement.repositories.NutritionInfoRepository;

@Component
public class NutritionInfoSeeder {
    @Autowired
    private NutritionInfoRepository nutritionInfoRepository;

    public void seed() {
        NutritionInfo nutri1 = NutritionInfo.builder().id(1L).nutritionName("Test 1").description("").build();
        NutritionInfo nutri2 = NutritionInfo.builder().id(2L).nutritionName("").description("").build();
        NutritionInfo nutri3 = NutritionInfo.builder().id(3L).nutritionName("").description("").build();
        NutritionInfo nutri4 = NutritionInfo.builder().id(4L).nutritionName("").description("").build();
        NutritionInfo nutri5 = NutritionInfo.builder().id(5L).nutritionName("").description("").build();
        nutritionInfoRepository.saveAll(Arrays.asList(new NutritionInfo[] {nutri1, nutri2, nutri3, nutri4, nutri5}));
    }
}
