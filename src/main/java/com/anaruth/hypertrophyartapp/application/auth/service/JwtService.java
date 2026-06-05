package com.anaruth.hypertrophyartapp.application.auth.service;

import com.anaruth.hypertrophyartapp.domain.auth.model.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    private static final String SECRET =
            "hypertrophy-art-secret-key-for-jwt-token-generation-2026";

    private static final long EXPIRATION_MILLIS =
            1000 * 60 * 60 * 24;

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public String generateToken(UUID id, String email, Role role) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_MILLIS);

        return Jwts.builder()
                .subject(email)
                .claim("id", id.toString())
                .claim("role", role.name())
                .issuedAt(now)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return extractClaims(token).getSubject();
    }

    public UUID extractId(String token) {
        String id = extractClaims(token).get("id", String.class);
        return UUID.fromString(id);
    }

    public Role extractRole(String token) {
        String role = extractClaims(token).get("role", String.class);
        return Role.valueOf(role);
    }

    public boolean isTokenValid(String token) {
        return extractClaims(token).getExpiration().after(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}