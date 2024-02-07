package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PetService {
    @Autowired
    private PetRepository petsRepository;

    public List<Pet> getPetsByUserId (Long userId) {
        return petsRepository.getPetsByUserId(userId);
    }

    public Pet addPet (Pet pet) {
        pet.setCreatedAt(new Date());
        pet.setIsActive(1L);
        return petsRepository.save(pet);
    }
}
