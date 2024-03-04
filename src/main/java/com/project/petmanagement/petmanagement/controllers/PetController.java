package com.project.petmanagement.petmanagement.controllers;
import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.payloads.responses.PetResponse;
import com.project.petmanagement.petmanagement.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping(value = "/getPet") //done
    public ResponseEntity<Object> getPet() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail userDetail = (JWTUserDetail) authentication.getPrincipal();
        List<Pet> pets = petService.getPetsByUserId(userDetail.getId());
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get list successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);

    }

    @PostMapping(value = "/addPet") //done
    public Object addPet(@RequestBody Pet pet) {
        if(validatePet(pet) != "") {
            return validatePet(pet);
        }
        Pet pets = petService.addPet(pet);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Add pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/updatePet") //done
    public ResponseEntity<Object> updatePet(@RequestBody Pet pet) {
        Pet pets = petService.updatePet(pet);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Update pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    @PostMapping(value = "/deletePet")
    public ResponseEntity<Object> deletePet(@RequestBody Pet pet) {
        Pet pets = petService.deletePet(pet);
        PetResponse response = PetResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Delete pet successfully")
                .data(pets)
                .build();
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    String validatePet(Pet pet) {
        String rs = "";
        if(pet.getFullname() == null) {
            rs += "Chưa có fullname";
        }
        if(pet.getUserId() == null) {
            rs += "Chưa có userId";
        }
        if(pet.getDateOfBirth() == null) {
            rs += "Chưa có ngày sinh";
        }
        if(pet.getSpeciesId() == null) {
            rs += "Chưa có Id giống loài";
        }
        if (pet.getDateOfBirth().after(new Date())) {
            rs += "Ngày sinh không hợp lệ";
        }
        return rs;
    }
}
