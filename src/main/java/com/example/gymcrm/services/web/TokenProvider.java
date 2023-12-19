package com.example.gymcrm.services.web;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenProvider {

    private final String secretKey = "YOUR_SECRET_KEY";
    private final long validityInMilliseconds = 3600000; // 1 hour

    public String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .compact();
    }
}