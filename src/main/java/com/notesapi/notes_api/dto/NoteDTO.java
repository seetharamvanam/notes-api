package com.notesapi.notes_api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notesapi.notes_api.model.User;

import java.util.Date;

public class NoteDTO {

    private String title;
    private String content;
    @JsonIgnore
    private User user;
    private Date createdAt;
    private Date updatedAt;
    private Long id;

    public NoteDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public NoteDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NoteDTO(String title, String content, Date createdAt, Date updatedAt, Long id) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
    }
}
