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
        Species s1 = Species.builder().id(1L).name("Labrador Retriever").build();
        Species s2 = Species.builder().id(2L).name("German Shepherd").build();
        Species s3 = Species.builder().id(3L).name("Golden Retriever").build();
        Species s4 = Species.builder().id(4L).name("Bulldog").build();
        Species s5 = Species.builder().id(5L).name("Beagle").build();
        Species s6 = Species.builder().id(6L).name("Poodle").build();
        Species s7 = Species.builder().id(7L).name("Siberian Husky").build();
        Species s8 = Species.builder().id(8L).name("Dachshund").build();
        Species s9 = Species.builder().id(9L).name("Boxer").build();
        Species s10 = Species.builder().id(10L).name("Shih Tzu").build();
        Species s11 = Species.builder().id(11L).name("Persian").build();
        Species s12 = Species.builder().id(12L).name("Siamese").build();
        Species s13 = Species.builder().id(13L).name("Maine Coon").build();
        Species s14 = Species.builder().id(14L).name("Ragdoll").build();
        Species s15 = Species.builder().id(15L).name("Bengal").build();
        Species s16 = Species.builder().id(16L).name("Sphynx").build();
        Species s17 = Species.builder().id(17L).name("Abyssinian").build();
        Species s18 = Species.builder().id(18L).name("Scottish Fold").build();
        Species s19 = Species.builder().id(19L).name("Burmese").build();
        Species s20 = Species.builder().id(20L).name("Norwegian Forest Cat").build();

        speciesRepository.saveAll(Arrays.asList(new Species[] { s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s13,
                s14, s15, s16, s17, s18, s19, s20 }));
    }
}
