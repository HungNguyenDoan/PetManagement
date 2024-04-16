package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Category;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/")
    public ResponseEntity<Object> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        DataResponse categoryResponse = DataResponse.builder()
                .message("Get list categories successfully")
                .status(HttpStatus.OK.value())
                .data(categories).build();
        return new ResponseEntity<Object>(categoryResponse,HttpStatus.OK);
    }
}
