package com.notesapi.notes_api.service;

import com.notesapi.notes_api.config.SecurityConfig;
import com.notesapi.notes_api.dto.UserAuthDTO;
import com.notesapi.notes_api.model.User;
import com.notesapi.notes_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String registerUser(UserAuthDTO userAuthDTO){
        User existingUser = userRepository.findByEmailAddress(userAuthDTO.getEmailAddress());
        if(existingUser!= null && existingUser.getEmailAddress().equalsIgnoreCase(userAuthDTO.getEmailAddress())){
            return "User email exists!";
        }
        else{
            String encodedPassword = passwordEncoder.encode(userAuthDTO.getPassword());
            User newUser = new User(userAuthDTO.getEmailAddress(),encodedPassword);
            userRepository.save(newUser);
            return "User Information saved successfully!";
        }

    }
}
