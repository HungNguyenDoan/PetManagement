package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Cart;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @GetMapping("/users")
    public ResponseEntity<Object> getCartByUser() {
        Cart cart = cartService.getCartByUser();
        if (cart == null) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .message("Can not find cart of user")
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        DataResponse cartResponse = DataResponse
                .builder()
                .status(HttpStatus.OK.value())
                .message("Get cart by user successfully")
                .data(cart)
                .build();
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addToCart(@RequestParam("product_id") Long productId, @RequestParam(name = "quantity", defaultValue = "1") Integer quantity) {
        try {
            Cart cart = cartService.addToCart(productId, quantity);
            if (cart == null) {
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Can not add item to cart")
                        .build();
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            DataResponse cartResponse = DataResponse
                    .builder()
                    .status(HttpStatus.CREATED.value())
                    .message("Add product to cart successfully")
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
    public ResponseEntity<Object> updateCart(@RequestParam("item_id") Long itemId, @RequestParam(name = "quantity") Integer quantity) {
        try {
            Cart cart = cartService.updateCart(itemId, quantity);
            if (cart == null) {
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Can not update cart")
                        .build();
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            DataResponse cartResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Update cart successfully")
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

    @DeleteMapping("/delete/cart_items/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable("id") Long itemId) {
        try {
            Cart cart = cartService.deleteItem(itemId);
            if (cart == null) {
                ErrorResponse errorResponse = ErrorResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Can not delete item to cart")
                        .build();
                return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            DataResponse cartResponse = DataResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Delete item in cart successfully")
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