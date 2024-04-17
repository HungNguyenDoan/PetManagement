package com.project.petmanagement.petmanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.Disease;
import com.project.petmanagement.petmanagement.repositories.DiseaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiseaseService {
    private final DiseaseRepository repository;

    public List<Disease> getAll() {
        return repository.findAll();
    }

    public Disease getDetail(Long id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Can not find nutritious food with ID: " + id));
    }
}
