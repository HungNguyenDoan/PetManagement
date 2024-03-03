package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.DTO.PetDTO;
import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.models.Pet;
import com.project.petmanagement.petmanagement.payloads.responses.Response;
import com.project.petmanagement.petmanagement.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;

    @GetMapping(value = "/getPet") //done
    public ResponseEntity<Object> getPet() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            JWTUserDetail userDetail = (JWTUserDetail) authentication.getPrincipal();
            List<Pet> pets = petService.getPetsByUserId(userDetail.getId());
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Không thể lấy được pet"));
        }
    }

    @PostMapping(value = "/addPet") //done
    public ResponseEntity<Object> addPet(@RequestBody Pet pet) {
        try {
            Pet pets = petService.addPet(pet);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Không thể add được pet"));
        }
    }

    @PostMapping(value = "/updatePet") //done
    public ResponseEntity<Object> updatePet(@RequestBody Pet pet) {
        try {
            Pet pets = petService.updatePet(pet);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Không thể cập nhật thông tin pet"));
        }
    }
    @PostMapping(value = "/deletePet")
    public ResponseEntity<Object> deletePet(@RequestBody Pet pet) {
        try {
            Pet pets = petService.deletePet(pet);
            return ResponseEntity.ok().body(pets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new Response(HttpStatus.UNAUTHORIZED.value(), "Không thể xóa Pet"));
        }
    }


}
