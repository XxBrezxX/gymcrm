package com.example.gymcrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.gymcrm.model.requests.LoginRequest;
import com.example.gymcrm.services.web.JwtTokenProvider;

@Controller
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String getMethodName() {
        return "home";
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        System.out.println("Received login request: " + request.getUsername()); // Agrega esta línea

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(request.getUsername());

            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            // Log the error message here.
            System.out.println("Failed to authenticate user: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Failed to authenticate user.");
        }
    }

}