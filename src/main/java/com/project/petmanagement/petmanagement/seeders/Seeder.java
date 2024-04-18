package com.project.petmanagement.petmanagement.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private final RoleSeeder roleSeeder;
    private final FoodTypeSeeder foodTypeSeeder;
    private final NutritiousFoodSeeder nutritiousFoodSeeder;
    private final SpeciesSeeder speciesSeeder;
    private final BreedSeeder breedSeeder;
    private final CategorySeeder categorySeeder;
    private final ProductSeeder productSeeder;
    private final VetSeeder vetSeeder;
    private final DiseaseSeeder diseaseSeeder;
    private final TreatmentSeeder treatmentSeeder;
  
    @Override
    public void run(String... args) throws Exception {
        roleSeeder.seed();
        foodTypeSeeder.seed();
        nutritiousFoodSeeder.seed();
        speciesSeeder.seed();
        breedSeeder.seed();
        categorySeeder.seed();
        productSeeder.seed();
        vetSeeder.seed();
        diseaseSeeder.seed();
        treatmentSeeder.seed();
    }
}
