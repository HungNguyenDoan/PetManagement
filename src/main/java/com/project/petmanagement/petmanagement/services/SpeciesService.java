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
        return speciesRepository.findAll();
    }

    public Species getDetailSpecies(Long id) {
        return speciesRepository.getReferenceById(id);
    }
}
