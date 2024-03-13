package com.project.petmanagement.petmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.models.Species;
import com.project.petmanagement.petmanagement.repositories.SpeciesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SpeciesService {
    private final SpeciesRepository speciesRepository;

    public List<Species> getAllSpecies() {
        List<Species> rootSpecies = speciesRepository.getAllSpecies(null);
        for(Species s : rootSpecies) {
            List<Species> children = speciesRepository.getAllSpecies(s.getId());
            s.setBreeds(children);
        }
        return rootSpecies;
    }

    public Species getDetailSpecies(Long id) {
        return speciesRepository.findById(id).get();
    }
}
