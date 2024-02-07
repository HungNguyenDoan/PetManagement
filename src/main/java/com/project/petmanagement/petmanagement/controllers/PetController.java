package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.DTO.PetDTO;
import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.payloads.responses.Response;
import com.project.petmanagement.petmanagement.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @RequestMapping(value = "/getPet")
    public ResponseEntity<Object> getPet(@RequestParam Long userId) {
        try {
            List<PetDTO> pets = petService.getPetsByUserId(userId);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Please check your username or password"));
        }
    }

    @RequestMapping(value = "/addPet")
    public ResponseEntity<Object> addPet(@RequestBody Pet pet) {
        try {
            Pet pets = petService.addPet(pet);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Please check your username or password"));
        }
    }

    @RequestMapping(value = "/updatePet")
    public ResponseEntity<Object> updatePet(@RequestBody Pet pet) {
        try {
            Pet pets = petService.updatePet(pet);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Please check your username or password"));
        }
    }
    @RequestMapping(value = "/deletePet")
    public ResponseEntity<Object> deletePet(@RequestBody Pet pet) {
        try {
            Pet pets = petService.deletePet(pet);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Please check your username or password"));
        }
    }


}
