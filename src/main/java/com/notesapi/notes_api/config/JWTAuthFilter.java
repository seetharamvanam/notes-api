package com.notesapi.notes_api.config;

import com.notesapi.notes_api.model.User;
import com.notesapi.notes_api.repository.UserRepository;
import com.notesapi.notes_api.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    JWTService jwtService;

    @Autowired
    UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(authHeader!= null && authHeader.startsWith("Bearer ")) {
            String jwtToken = authHeader.substring(7);
            String email = jwtService.extractUserName(jwtToken);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userRepository.findByEmailAddress(email);
                if (user != null) {
                    SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken.authenticated(user, null, Collections.emptyList()));
                }
            }
        }

        filterChain.doFilter(request,response);
        return;
    }
}
