package com.project.petmanagement.petmanagement.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final RoleSeeder roleSeeder;
    private final FoodTypeSeeder foodTypeSeeder;
    private final NutritionInfoSeeder nutritionInfoSeeder;
    private final SpeciesSeeder speciesSeeder;

    @Override
    public void run(String... args) throws Exception {
        speciesSeeder.seed();
        roleSeeder.seed();
        foodTypeSeeder.seed();
        nutritionInfoSeeder.seed();
    }

}
