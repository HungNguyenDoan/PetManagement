package com.project.petmanagement.petmanagement.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.project.petmanagement.petmanagement.JWT.JWTTokenProvider;
import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.payloads.requests.LoginRequest;
import com.project.petmanagement.petmanagement.payloads.requests.RegisterRequest;
import com.project.petmanagement.petmanagement.payloads.responses.LoginResponse;
import com.project.petmanagement.petmanagement.payloads.responses.Response;
import com.project.petmanagement.petmanagement.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTTokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            String jwt = tokenProvider.generateToken((JWTUserDetail) authentication.getPrincipal());
            LoginResponse response = LoginResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("Login successfully")
                    .token(jwt)
                    .data(((JWTUserDetail) authentication.getPrincipal()).getUser())
                    .build();
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(
                    new Response(HttpStatus.UNAUTHORIZED.value(), "Please check your username or password"),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequest request) {
        userService.register(request);
        return login(new LoginRequest(request.getPhonenumber(), request.getPassword()));
    }
}
