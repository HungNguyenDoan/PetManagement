package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.entity.FoodType;
import com.project.petmanagement.petmanagement.models.entity.NutritiousFood;
import com.project.petmanagement.petmanagement.repositories.FoodTypeRepository;
import com.project.petmanagement.petmanagement.repositories.NutritiousFoodRepository;

@Component
public class NutritiousFoodSeeder {
    @Autowired
    private NutritiousFoodRepository NutritiousFoodRepository;

    @Autowired
    private FoodTypeRepository foodTypeRepository;

    public void seed() {
        List<FoodType> listFoodTypes = foodTypeRepository.findAll();

        NutritiousFood nutri1 = NutritiousFood.builder()
                .id(1L)
                .name("Acme Healthy Bites")
                .description("Premium dry kibble enriched with vitamins and minerals for overall health.")
                .build();
        nutri1.setFoodType(listFoodTypes.get(0));

        NutritiousFood nutri2 = NutritiousFood.builder()
                .id(2L)
                .name("NutriPaws Chicken Delight")
                .description("Canned wet food with succulent chicken pieces in savory gravy.")
                .build();
        nutri2.setFoodType(listFoodTypes.get(1));

        NutritiousFood nutri3 = NutritiousFood.builder()
                .id(3L)
                .name("NaturiMix Salmon Feast")
                .description("Raw food with a blend of fresh salmon and essential nutrients.")
                .build();
        nutri3.setFoodType(listFoodTypes.get(2));

        NutritiousFood nutri4 = NutritiousFood.builder()
                .id(4L)
                .name("Purrfection Beef Blend")
                .description("Delicious blend of beef, rice, and veggies for a balanced diet.")
                .build();
        nutri4.setFoodType(listFoodTypes.get(3));

        NutritiousFood nutri5 = NutritiousFood.builder()
                .id(5L)
                .name("WhiskerWell Grain-Free Medley")
                .description("Grain-free kibble with a mix of real meat and vegetables.")
                .build();
        nutri5.setFoodType(listFoodTypes.get(5));

        NutritiousFood nutri6 = NutritiousFood.builder()
                .id(6L)
                .name("VetSelect Prescription Diet")
                .description("Veterinary-prescribed diet for specific health conditions.")
                .build();
        nutri6.setFoodType(listFoodTypes.get(4));

        NutritiousFood nutri7 = NutritiousFood.builder()
                .id(7L)
                .name("Organic Paws Turkey and Quinoa")
                .description("Organic dry food with turkey and quinoa for a wholesome meal.")
                .build();
        nutri7.setFoodType(listFoodTypes.get(6));

        NutritiousFood nutri8 = NutritiousFood.builder()
                .id(8L)
                .name("DentalDelish Minty Crunch")
                .description("Dental chews designed to promote oral health with a minty flavor.")
                .build();
        nutri8.setFoodType(listFoodTypes.get(8));

        NutritiousFood nutri9 = NutritiousFood.builder()
                .id(9L)
                .name("RAWvolution Beef Barf")
                .description("Freeze-dried raw food with real beef, perfect for a natural diet.")
                .build();
        nutri9.setFoodType(listFoodTypes.get(2));

        NutritiousFood nutri10 = NutritiousFood.builder()
                .id(10L)
                .name("TastyTreats Cheese Delight")
                .description("Semi-moist treats with a cheese flavor, ideal for training sessions.")
                .build();
        nutri10.setFoodType(listFoodTypes.get(9));

        NutritiousFoodRepository.saveAll(
                Arrays.asList(nutri1, nutri2, nutri3, nutri4, nutri5, nutri6, nutri7, nutri8, nutri9, nutri10));
    }
}
