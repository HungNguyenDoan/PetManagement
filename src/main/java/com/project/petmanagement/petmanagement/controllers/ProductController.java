package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Product;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;
    @GetMapping("/")
    private ResponseEntity<Object> getAllProduct(){
        List<Product> productList = productService.getAllProduct();
        DataResponse productResponse = DataResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get Products successfully")
                .data(productList)
                .build();
        return new ResponseEntity<Object> (productResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductDetail(@PathVariable("id") Long idProduct) throws Exception {
        Product product = productService.getProductDetail(idProduct);
        DataResponse productResponse = DataResponse.builder()
                .status(HttpStatus.OK.value())
                .message("Get product successfully")
                .data(product)
                .build();
        return  new ResponseEntity<Object>(productResponse, HttpStatus.OK);
    }
}