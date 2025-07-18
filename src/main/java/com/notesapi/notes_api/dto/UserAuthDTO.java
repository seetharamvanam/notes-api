package com.notesapi.notes_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserAuthDTO {
    @NotBlank(message = "Email Address cannot be blank.")
    @Email
    private String emailAddress;
    @NotBlank(message = "Password cannot be blank.")
    private String password;
    public UserAuthDTO(String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAuthDTO(){

    }
}
