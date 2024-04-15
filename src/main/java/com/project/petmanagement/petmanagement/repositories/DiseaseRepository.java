package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.Disease;
import com.project.petmanagement.petmanagement.models.entity.Species;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    List<Disease> findBySpecies(Species species);

    List<Disease> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrSymptomsContainingIgnoreCase(String name, String description, String symptoms);
}
