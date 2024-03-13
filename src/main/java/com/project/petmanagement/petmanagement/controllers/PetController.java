package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.models.Species;
import com.project.petmanagement.petmanagement.payloads.requests.PetRequest;
import com.project.petmanagement.petmanagement.payloads.responses.PetResponse;
import com.project.petmanagement.petmanagement.payloads.responses.Response;
import com.project.petmanagement.petmanagement.services.PetService;
import com.project.petmanagement.petmanagement.services.SpeciesService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @Autowired
    private SpeciesService speciesService;

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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDetailPet(@PathVariable Long id) {
        Pet pet = petService.getDetailPet(id);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get list successfully")
                .data(pet)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }
    @PostMapping(value = "/addPet") // done
    public Object addPet(@RequestBody @Valid PetRequest petRequest) {
        Species species = speciesService.getDetailSpecies(petRequest.getSpeciesId());
        if(species == null) {
            Response response = Response.builder().status(404).message("Species is not found").build();
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
        Pet pet = Pet.builder()
                .fullname(petRequest.getName())
                .dateOfBirth(petRequest.getDob())
                .species(species)
                .description(petRequest.getDescription())
                .avatar(petRequest.getAvatar())
                .gender(petRequest.getGender())
                .neutered(petRequest.getNeutered())
                .build();
        Pet pets = petService.addPet(pet);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Add pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/updatePet/{id}") // done
    public ResponseEntity<Object> updatePet(@RequestBody @Valid PetRequest petRequest, @PathVariable Long id) {
        Pet pet = petService.getDetailPet(id);
        if (pet == null) {
            Response response = Response.builder().status(404).message("Pet is not found").build();
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
        Species species = speciesService.getDetailSpecies(petRequest.getSpeciesId());
        if(species == null) {
            Response response = Response.builder().status(404).message("Species is not found").build();
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
        pet.setAvatar(petRequest.getAvatar());
        pet.setDateOfBirth(petRequest.getDob());
        pet.setDescription(petRequest.getDescription());
        pet.setFullname(petRequest.getName());
        pet.setGender(petRequest.getGender());
        pet.setNeutered(petRequest.getNeutered());
        pet.setSpecies(species);
        pet.setUpdatedAt(new Date());
        pet = petService.updatePet(pet);
        if(pet == null) {
            Response response = Response.builder()
                                    .status(500)
                                    .message("Cannot update please try again")
                                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Update pet successfully")
                .data(pet)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/deletePet/{petId}")
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
