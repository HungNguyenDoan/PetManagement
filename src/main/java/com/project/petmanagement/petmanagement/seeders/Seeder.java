package com.project.petmanagement.petmanagement.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final RoleSeeder roleSeeder;
    private final FoodTypeSeeder foodTypeSeeder;
    private final NutritiousFoodSeeder nutritionInfoSeeder;
    private final SpeciesSeeder speciesSeeder;
    private final BreedSeeder breedSeeder;

    @Override
    public void run(String... args) throws Exception {
        speciesSeeder.seed();
        roleSeeder.seed();
        foodTypeSeeder.seed();
        nutritionInfoSeeder.seed();
        breedSeeder.seed();
    }

}
