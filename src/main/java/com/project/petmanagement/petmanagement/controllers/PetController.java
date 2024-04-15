package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Breed;
import com.project.petmanagement.petmanagement.models.entity.Pet;
import com.project.petmanagement.petmanagement.models.entity.Species;
import com.project.petmanagement.petmanagement.payloads.requests.PetRequest;
import com.project.petmanagement.petmanagement.payloads.responses.PetResponse;
import com.project.petmanagement.petmanagement.payloads.responses.Response;
import com.project.petmanagement.petmanagement.services.BreedService;
import com.project.petmanagement.petmanagement.services.PetService;
import com.project.petmanagement.petmanagement.services.SpeciesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pets")
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("/users/") // done
    public ResponseEntity<Object> getPetsByUser() {
        List<Pet> pets = petService.getPetsByUser();
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get pets by user successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPetDetails(@PathVariable("id") Long petId) {
        Pet pet = null;
        try {
            pet = petService.getPet(petId);
            PetResponse response = PetResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Get pet details successfully")
                    .data(pet)
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            PetResponse response = PetResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }


    }

    @PostMapping("/") // done
    public ResponseEntity<Object> addPet(@RequestBody @Valid PetRequest petRequest) {
        try {
            Pet pet = petService.addPet(petRequest);
            if (pet == null) {
                Response response = Response.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Cannot add pet. Please try again!")
                        .build();
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }
            PetResponse response = PetResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Add pet successfully")
                    .data(pet)
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            PetResponse response = PetResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}") // done
    public ResponseEntity<Object> updatePet(@RequestBody @Valid PetRequest petRequest, @PathVariable Long id) {
        Pet pet = null;
        try {
            pet = petService.updatePet(id, petRequest);
            if (pet == null) {
                Response response = Response.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message("Cannot update. Please try again")
                        .build();
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }
            PetResponse response = PetResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Update pet successfully")
                    .data(pet)
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response response = Response.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> deletePet(@PathVariable("id") Long petId) {
        Pet pet = null;
        try {
            pet = petService.deletePet(petId);
            PetResponse response = PetResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Delete pet successfully")
                    .data(pet)
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            Response response = Response.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
