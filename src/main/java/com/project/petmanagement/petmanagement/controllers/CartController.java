package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Cart;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.CartService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/users/")
    public ResponseEntity<?> getCart() {
        Cart cart = cartService.getCart();
        DataResponse cartResponse = DataResponse
                .builder()
                .data(cart)
                .status(HttpStatus.OK.value())
                .message("Get cart successfully.")
                .build();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestParam("idProduct") Long idProduct, @RequestParam(name = "quantity", defaultValue = "1") int quantity)  {
        try {
            Cart cart = cartService.addToCart(idProduct, quantity);
            DataResponse cartResponse = DataResponse
                    .builder()
                    .status(HttpStatus.CREATED.value())
                    .message("Add product to cart success")
                    .data(cart)
                    .build();
            return new ResponseEntity<>(cartResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateCart(@RequestParam("idItem") Long idItem, @RequestParam(name = "quantity") int quantity) {
        try {
            Cart cart = cartService.updateCart(idItem, quantity);
            DataResponse cartResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Update cart success.")
                    .data(cart)
                    .build();
            return new ResponseEntity<>(cartResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete/cart-item/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long idItem){
        try {
            Cart cart = cartService.deleteItem(idItem);
            DataResponse cartResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Delete item success")
                    .data(cart)
                    .build();
            return new ResponseEntity<>(cartResponse, HttpStatus.OK);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }
}