package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.payloads.requests.PetRequest;
import com.project.petmanagement.petmanagement.payloads.responses.PetResponse;
import com.project.petmanagement.petmanagement.payloads.responses.Response;
import com.project.petmanagement.petmanagement.services.PetService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
@Slf4j
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping(value = "/getPet") // done
    public ResponseEntity<Object> getPet() {
        List<Pet> pets = petService.getPetsByUserId();
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get list successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }

    @PostMapping(value = "/addPet") // done
    public Object addPet(@RequestBody @Valid PetRequest petRequest) {
        Pet pet = Pet.builder()
                .fullname(petRequest.getName())
                .dateOfBirth(petRequest.getDob())
                .speciesId(petRequest.getSpeciesId())
                .description(petRequest.getDescription())
                .build();
        Pet pets = petService.addPet(pet);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Add pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/getDetailPet")
    public ResponseEntity<Object> getDetailPet(@RequestBody PetRequest petRequest ){
        Pet pets = petService.getDetailPet(petRequest.getId());
        return new ResponseEntity<Object>(pets, HttpStatus.OK);
    }

    @PostMapping(value = "/updatePet/{id}") // done
    public ResponseEntity<Object> updatePet(@RequestBody PetRequest petRequest, @PathVariable Long id) {
        Pet pets = petService.getDetailPet(id);
        // if (pets == null) {
        //     Response response = Response.builder().status(404).message("Pet is not found").build();
        //     return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        // }
        // pets = petService.updatePet(pets);
        // if(pets == null) {
        //     Response response = Response.builder()
        //                             .status(500)
        //                             .message("Cannot update please try again")
        //                             .build();
        //     return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        // }
        log.info(pets.toString());
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Update pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deletePet/petId")
    public ResponseEntity<Object> deletePet(@PathVariable Long petId) {
        Pet pets = petService.deletePet(petId);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Delete pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}
