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
    @Override
    public void run(String... args) throws Exception {
        roleSeeder.seed();
        foodTypeSeeder.seed();
        nutritionInfoSeeder.seed();
    }

}
