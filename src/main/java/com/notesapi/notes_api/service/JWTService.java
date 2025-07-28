package com.notesapi.notes_api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class JWTService {

    private final String SECRET_KEY="my_life_sucks_!_I_am_a_looser_learning_Spring_Boot!";

    private Key getSignInKey(){
        byte[] keyBytes = SECRET_KEY.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token){
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).
                getBody().getSubject();
    }

    public String generateToken(String email){
        return Jwts.builder().setSubject(email).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis()+10000*60*60*24)).
                signWith(getSignInKey(), SignatureAlgorithm.HS256).
                compact();

    }
}
