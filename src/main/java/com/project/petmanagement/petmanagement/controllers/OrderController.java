package com.project.petmanagement.petmanagement.controllers;

import com.project.petmanagement.petmanagement.models.entity.Cart;
import com.project.petmanagement.petmanagement.models.entity.Order;
import com.project.petmanagement.petmanagement.payloads.requests.OrderRequest;
import com.project.petmanagement.petmanagement.payloads.responses.DataResponse;
import com.project.petmanagement.petmanagement.payloads.responses.ErrorResponse;
import com.project.petmanagement.petmanagement.services.CartService;
import com.project.petmanagement.petmanagement.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;
    @GetMapping("/users/")
    public ResponseEntity<?> getOrderUser(){
        List<Order> orders = orderService.getOrderByUser();
        DataResponse orderResponse = DataResponse.builder().status(HttpStatus.OK.value()).message("Get order successfully").data(orders).build();
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest orderRequest){
        try {
            Order order = orderService.createOrder(orderRequest);
            DataResponse orderResponse = DataResponse.builder()
                    .status(HttpStatus.CREATED.value())
                    .message("create order successfully")
                    .data(order)
                    .build();
            return  new ResponseEntity<>(orderResponse,HttpStatus.CREATED);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message(e.getMessage())
                    .build();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

    }
}