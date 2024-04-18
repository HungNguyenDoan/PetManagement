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
    private final VetSeeder vetSeeder;
    private final DiseaseSeeder diseaseSeeder;
    private final TreatmentSeeder treatmentSeeder;
    private final DailyActivitySeeder dailyActivitySeeder;
    private final VaccineSeeder vaccineSeeder;

    @Override
    public void run(String... args) throws Exception {
        speciesSeeder.seed();
        roleSeeder.seed();
        foodTypeSeeder.seed();
        nutritionInfoSeeder.seed();
        breedSeeder.seed();
        vetSeeder.seed();
        diseaseSeeder.seed();
        treatmentSeeder.seed();
        dailyActivitySeeder.seed();
        vaccineSeeder.seed();
    }

}
