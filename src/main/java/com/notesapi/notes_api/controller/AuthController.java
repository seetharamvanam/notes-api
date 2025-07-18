package com.notesapi.notes_api.controller;

import com.notesapi.notes_api.dto.UserAuthDTO;
import com.notesapi.notes_api.model.User;
import com.notesapi.notes_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<String> registerUser(@RequestBody UserAuthDTO userAuthDTO){
        return authService.registerUser(userAuthDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> loginUser(@RequestBody UserAuthDTO userAuthDTO){
        return authService.loginUser(userAuthDTO);
    }

}
