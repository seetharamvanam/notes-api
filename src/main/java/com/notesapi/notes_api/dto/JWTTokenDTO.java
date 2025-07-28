package com.notesapi.notes_api.dto;

public class JWTTokenDTO {
    private String token;
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public JWTTokenDTO(String token,Boolean status) {
        this.token = token;
        this.status = status;
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
