package com.notesapi.notes_api.controller;

import com.notesapi.notes_api.dto.NoteDTO;
import com.notesapi.notes_api.model.Note;
import com.notesapi.notes_api.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getAllNotesForCurrentUser(){
        return noteService.getAllNotesForCurrentUser();
    }

    @PostMapping("/notes/create")
    public NoteDTO createNewNote(@RequestBody NoteDTO noteDTO){
       return noteService.createNote(noteDTO);
    }

    @GetMapping("notes/{id}")
    public NoteDTO fetchNotesByIdForUser(@PathVariable Long id){return noteService.fetchsingleNote(id);}
}
