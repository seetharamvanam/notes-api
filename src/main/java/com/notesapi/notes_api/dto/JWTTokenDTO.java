package com.notesapi.notes_api.dto;

public class JWTTokenDTO {
    String token;

    public JWTTokenDTO(String token) {
        this.token = token;
    }

    public JWTTokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
