package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.repositories.BreedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BreedService {
    private final BreedRepository breedRepository;
}
