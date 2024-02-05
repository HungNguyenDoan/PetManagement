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
        NutritionInfo nutri1 = NutritionInfo.builder().id(1L).build();
        NutritionInfo nutri2 = NutritionInfo.builder().id(2L).build();
        NutritionInfo nutri3 = NutritionInfo.builder().id(3L).build();
        NutritionInfo nutri4 = NutritionInfo.builder().id(4L).build();
        NutritionInfo nutri5 = NutritionInfo.builder().id(5L).build();
        nutritionInfoRepository.saveAll(Arrays.asList(new NutritionInfo[] {nutri1, nutri2, nutri3, nutri4, nutri5}));
    }
}
