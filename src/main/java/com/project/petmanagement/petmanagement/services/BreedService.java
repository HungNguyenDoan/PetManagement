package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.Breed;
import com.project.petmanagement.petmanagement.models.entity.Species;
import com.project.petmanagement.petmanagement.repositories.BreedRepository;
import com.project.petmanagement.petmanagement.repositories.SpeciesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BreedService {
    private final SpeciesRepository speciesRepository;
    private final BreedRepository breedRepository;

    public List<Breed> getBreedsBySpecies(Species species) {
        return breedRepository.findBySpecies(species);
    }

    public Breed getBreedDetails(Long breedId) throws Exception {
        return breedRepository.findById(breedId).orElseThrow(() -> new DataNotFoundException("Can not find breed with ID: " + breedId));
    }
}
