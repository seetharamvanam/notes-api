package com.notesapi.notes_api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private final String plainString = "my_life_sucks_!_I_am_a_looser_learning_Spring_Boot!";;

    private Key getSignInKey(){
        return Keys.hmacShaKeyFor(plainString.getBytes());
    }

    public String generateToken(String email){
        return Jwts.builder().setSubject(email).
                setIssuedAt(new Date()).
                setExpiration(new Date(System.currentTimeMillis()+10000*60*60*24)).
                signWith(getSignInKey(), SignatureAlgorithm.HS256).
                compact();
    }
}
