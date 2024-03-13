package com.project.petmanagement.petmanagement.seeders;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.petmanagement.petmanagement.models.Species;
import com.project.petmanagement.petmanagement.repositories.SpeciesRepository;

@Component
public class SpeciesSeeder {
    @Autowired
    private SpeciesRepository speciesRepository;

    public void seed() {
        Species dog = Species.builder().id(1L).name("dog").root(null).build();
        Species cat = Species.builder().id(2L).name("cat").root(null).build();
        Species s1 = Species.builder().id(3L).name("Labrador Retriever").root(dog).build();
        Species s2 = Species.builder().id(4L).name("German Shepherd").root(dog).build();
        Species s3 = Species.builder().id(5L).name("Golden Retriever").root(dog).build();
        Species s4 = Species.builder().id(6L).name("Bulldog").root(dog).build();
        Species s5 = Species.builder().id(7L).name("Beagle").root(dog).build();
        Species s6 = Species.builder().id(8L).name("Poodle").root(dog).build();
        Species s7 = Species.builder().id(9L).name("Siberian Husky").root(dog).build();
        Species s8 = Species.builder().id(10L).name("Dachshund").root(dog).build();
        Species s9 = Species.builder().id(11L).name("Boxer").root(dog).build();
        Species s10 = Species.builder().id(12L).name("Shih Tzu").root(dog).build();
        Species s11 = Species.builder().id(13L).name("Persian").root(cat).build();
        Species s12 = Species.builder().id(14L).name("Siamese").root(cat).build();
        Species s13 = Species.builder().id(15L).name("Maine Coon").root(cat).build();
        Species s14 = Species.builder().id(16L).name("Ragdoll").root(cat).build();
        Species s15 = Species.builder().id(17L).name("Bengal").root(cat).build();
        Species s16 = Species.builder().id(18L).name("Sphynx").root(cat).build();
        Species s17 = Species.builder().id(19L).name("Abyssinian").root(cat).build();
        Species s18 = Species.builder().id(20L).name("Scottish Fold").root(cat).build();
        Species s19 = Species.builder().id(21L).name("Burmese").root(cat).build();
        Species s20 = Species.builder().id(22L).name("Norwegian Forest Cat").root(cat).build();

        speciesRepository
                .saveAll(Arrays.asList(new Species[] { dog, cat, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13,
                        s14, s15, s16, s17, s18, s19, s20 }));
    }
}
