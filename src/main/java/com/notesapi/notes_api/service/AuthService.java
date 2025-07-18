package com.notesapi.notes_api.service;

import com.notesapi.notes_api.config.SecurityConfig;
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

    public ResponseEntity<String> registerUser(UserAuthDTO userAuthDTO){
        User existingUser = userRepository.findByEmailAddress(userAuthDTO.getEmailAddress());
        if(existingUser!= null && existingUser.getEmailAddress().equalsIgnoreCase(userAuthDTO.getEmailAddress())){
            return new ResponseEntity<>("User with provided email address already exists!", HttpStatus.CONFLICT);
        }
        else{
            String encodedPassword = passwordEncoder.encode(userAuthDTO.getPassword());
            User newUser = new User(userAuthDTO.getEmailAddress(),encodedPassword);
            userRepository.save(newUser);
            return new ResponseEntity<>("User registered successfully", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<String> loginUser(UserAuthDTO userAuthDTO){
        User user = userRepository.findByEmailAddress(userAuthDTO.getEmailAddress());
        if (user==null){
            return new ResponseEntity<>("User with provided email address does not exists!",HttpStatus.CONFLICT);
        }else if(passwordEncoder.matches(userAuthDTO.getPassword(),user.getPassword())){
            return new ResponseEntity<>("User logged in Successfully!",HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
}
