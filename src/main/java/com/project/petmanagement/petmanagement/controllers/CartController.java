package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Cart;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.services.CartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/get")
    public ResponseEntity<?> getCart() {
        Cart cart = cartService.getCart();
        DataResponse cartResponse = DataResponse.builder().data(cart).status(200).message("Get cart successfully.").build();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestParam("idProduct") Long idProduct, @RequestParam(name = "quantity", defaultValue = "1") int quantity){
        Cart cart = cartService.addToCart(idProduct, quantity);
        DataResponse cartResponse = DataResponse.builder().status(201).message("Add product to cart success").data(cart).build();
        return new ResponseEntity<>(cartResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCart(@RequestParam("idItem") Long idItem, @RequestParam(name = "quantity") int quantity){
        Cart cart = cartService.updateCart(idItem, quantity);
        DataResponse cartResponse = DataResponse.builder().status(200).message("Update cart success").data(cart).build();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long idItem){
        Cart cart = cartService.deleteItem(idItem);
        DataResponse cartResponse = DataResponse.builder().status(200).message("Delete item success").data(cart).build();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
}