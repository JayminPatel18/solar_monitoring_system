package com.solar.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "mySuperSecretKeyForJwtAuthentication1234567890";
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate Token
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Email
    public String extractEmail(String token){
        return extractClaims(token).getSubject();
    }

    // Validate token
    public boolean validateToken(String token, String email){
        String extractedEmail = extractEmail(token);

        return extractedEmail.equals(email)
                && !isTokenExpired(token);
    }

    // Check Expiration
    private boolean isTokenExpired(String token){
        return extractClaims(token)
                .getExpiration()
                .before(new Date());
    }

    // Extract Claims
    private Claims extractClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


}
