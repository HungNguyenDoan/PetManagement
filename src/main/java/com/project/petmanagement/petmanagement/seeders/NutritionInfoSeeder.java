package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;
import java.util.List;

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
                List<FoodType> listFoodTypes = foodTypeRepository.findAll();
                NutritionInfo nutri1 = NutritionInfo.builder().id(1L)
                                .nutritionName("Acme Healthy Bites")
                                .description("Premium dry kibble enriched with vitamins and minerals for overall health.")
                                .build();
                nutri1.setFoodType(listFoodTypes.get(0));
                NutritionInfo nutri2 = NutritionInfo.builder()
                                .id(2L)
                                .nutritionName("NutriPaws Chicken Delight")
                                .description("Canned wet food with succulent chicken pieces in savory gravy.")
                                .build();
                nutri2.setFoodType(listFoodTypes.get(1));
                NutritionInfo nutri3 = NutritionInfo.builder()
                                .id(3L)
                                .nutritionName("NaturiMix Salmon Feast")
                                .description("Raw food with a blend of fresh salmon and essential nutrients.")
                                .build();
                nutri3.setFoodType(listFoodTypes.get(2));
                NutritionInfo nutri4 = NutritionInfo.builder().id(4L).nutritionName("Purrfection Beef Blend")
                                .description("Delicious blend of beef, rice, and veggies for a balanced diet.")
                                .build();
                nutri4.setFoodType(listFoodTypes.get(3));
                NutritionInfo nutri5 = NutritionInfo.builder().id(5L).nutritionName("WhiskerWell Grain-Free Medley")
                                .description("Grain-free kibble with a mix of real meat and vegetables.").build();
                nutri5.setFoodType(listFoodTypes.get(5));
                NutritionInfo nutri6 = NutritionInfo.builder().id(6L).nutritionName("VetSelect Prescription Diet")
                                .description("Veterinary-prescribed diet for specific health conditions.").build();
                nutri6.setFoodType(listFoodTypes.get(4));
                NutritionInfo nutri7 = NutritionInfo.builder().id(7L).nutritionName("Organic Paws Turkey and Quinoa")
                                .description("Organic dry food with turkey and quinoa for a wholesome meal.").build();
                nutri7.setFoodType(listFoodTypes.get(6));
                NutritionInfo nutri8 = NutritionInfo.builder().id(8L).nutritionName("DentalDelish Minty Crunch")
                                .description("Dental chews designed to promote oral health with a minty flavor.")
                                .build();
                nutri8.setFoodType(listFoodTypes.get(8));
                NutritionInfo nutri9 = NutritionInfo.builder().id(9L).nutritionName("RAWvolution Beef Barf")
                                .description("Freeze-dried raw food with real beef, perfect for a natural diet.")
                                .build();
                nutri9.setFoodType(listFoodTypes.get(2));
                NutritionInfo nutri10 = NutritionInfo.builder().id(10L).nutritionName("TastyTreats Cheese Delight")
                                .description("Semi-moist treats with a cheese flavor, ideal for training sessions.")
                                .build();
                nutri10.setFoodType(listFoodTypes.get(9));
                nutritionInfoRepository
                                .saveAll(Arrays.asList(new NutritionInfo[] { nutri1, nutri2, nutri3, nutri4, nutri5,
                                                nutri6, nutri7, nutri8, nutri9, nutri10 }));
        }
}
