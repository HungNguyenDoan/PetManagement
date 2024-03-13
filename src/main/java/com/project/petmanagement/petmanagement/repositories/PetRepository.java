package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query(value = "SELECT * " +
            "FROM pets p " +
            "WHERE p.user_id = :userId AND p.is_active = 1 " +
            "ORDER BY p.id DESC", nativeQuery = true)
    List<Pet> getPetsByUserId(@Param("userId") Long userId);

    @Query(value = "Select * from pets p where p.id = :id and p.is_active = 1", nativeQuery = true )
    Pet getDetailPet(@Param("id") Long id);
}
