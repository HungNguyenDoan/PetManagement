package com.project.petmanagement.petmanagement.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.models.entity.Species;
import com.project.petmanagement.petmanagement.payloads.responses.SpeciesResponse;
import com.project.petmanagement.petmanagement.services.SpeciesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/species")
@RequiredArgsConstructor
public class SpeciesController {
    private final SpeciesService speciesService;

    @GetMapping("/all")
    public ResponseEntity<Object> getAllSpecies() {
        List<Species> listSpecies = speciesService.getAllSpecies();
        SpeciesResponse response = SpeciesResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get all species successfully")
                .data(listSpecies)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
