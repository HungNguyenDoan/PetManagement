package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.repositories.PetRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PetService {
    @Autowired
    private PetRepository petsRepository;

    public List<Pet> getPetsByUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JWTUserDetail userDetail = (JWTUserDetail) authentication.getPrincipal();
            return petsRepository.getPetsByUserId(userDetail.getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pet addPet(Pet pet) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JWTUserDetail userDetail = (JWTUserDetail) authentication.getPrincipal();
            pet.setUserId(userDetail.getId());
            pet.setCreatedAt(new Date());
            pet.setUpdatedAt(new Date());
            pet.setIsActive(1);
            return petsRepository.saveAndFlush(pet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pet getDetailPet(Long id) {
        try {
            return petsRepository.getDetailPet(id);
        } catch (Exception e) {
            return null;
        }
    }
    @Transactional
    public Pet updatePet(Pet pet) {
        try {
            return petsRepository.saveAndFlush(pet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Pet deletePet(Long petId) {
        try {
            Pet pet = petsRepository.getReferenceById(petId);
            pet.setIsActive(0);
            return petsRepository.saveAndFlush(pet);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
