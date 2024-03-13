package com.project.petmanagement.petmanagement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.petmanagement.petmanagement.models.Species;

public interface SpeciesRepository extends JpaRepository<Species, Long> {
    @Query(value = "SELECT * FROM species WHERE (parent_id IS NULL AND :id IS NULL) or (parent_id = :id)", nativeQuery = true)
    List<Species> getAllSpecies(@Param("id") Long id);
}
