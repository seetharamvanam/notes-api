package com.notesapi.notes_api.service;

import com.notesapi.notes_api.config.SecurityConfig;
import com.notesapi.notes_api.dto.JWTTokenDTO;
import com.notesapi.notes_api.dto.UserAuthDTO;
import com.notesapi.notes_api.model.User;
import com.notesapi.notes_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    public ResponseEntity<String> registerUser(UserAuthDTO userAuthDTO){
        User existingUser = userRepository.findByEmailAddress(userAuthDTO.getEmailAddress());
        if(existingUser!= null && existingUser.getEmailAddress().equalsIgnoreCase(userAuthDTO.getEmailAddress())){
            return new ResponseEntity<>("User with provided email address already exists!", HttpStatus.CONFLICT);
        }
        else{
            String encodedPassword = passwordEncoder.encode(userAuthDTO.getPassword());
            User newUser = new User(userAuthDTO.getEmailAddress(),encodedPassword);
            userRepository.save(newUser);
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
    }

    public ResponseEntity<JWTTokenDTO> loginUser(UserAuthDTO userAuthDTO){
        User user = userRepository.findByEmailAddress(userAuthDTO.getEmailAddress());
        if (user==null){

            return new ResponseEntity<>(new JWTTokenDTO("User with provided email address does not exists",Boolean.FALSE),HttpStatus.UNAUTHORIZED);
        }else if(passwordEncoder.matches(userAuthDTO.getPassword(),user.getPassword())){
            return new ResponseEntity<>(new JWTTokenDTO(jwtService.generateToken(user.getEmailAddress()),Boolean.TRUE),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new JWTTokenDTO("Invalid credentials",Boolean.FALSE), HttpStatus.UNAUTHORIZED);
        }
    }
}
