package com.notesapi.notes_api.service;

import com.notesapi.notes_api.dto.NoteDTO;
import com.notesapi.notes_api.model.Note;
import com.notesapi.notes_api.model.User;
import com.notesapi.notes_api.repository.NoteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class NoteService {

    @Autowired
    NoteRepository noteRepository;

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public List<Note> getAllNotesForCurrentUser(){
        return noteRepository.findByUser(getCurrentUser());
    }

    public NoteDTO createNote(NoteDTO noteDTO){
        Note note = new Note();
        note.setUser(getCurrentUser());
        note.setCreatedAt(new Date());
        note.setUpdatedAt(new Date());
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        Note savedNote = noteRepository.save(note);
        return new NoteDTO(savedNote.getTitle(), savedNote.getContent()
        ,savedNote.getCreatedAt(), savedNote.getUpdatedAt(), savedNote.getId());
    }

   public NoteDTO fetchsingleNote(long id) throws RuntimeException{
        Note note = noteRepository.findByIdAndUser(id, getCurrentUser());
        if(note == null){
            throw new RuntimeException("Note not found!");
        }
       return new NoteDTO(note.getTitle(), note.getContent()
               ,note.getCreatedAt(), note.getUpdatedAt(), note.getId());
   }

   public NoteDTO updateSpecificNotes(long id, NoteDTO noteDTO) throws RuntimeException{
        Note note = noteRepository.findByIdAndUser(id, getCurrentUser());
        if(note == null){
            throw new RuntimeException("Note not found!");
        }
        if (!noteDTO.getTitle().isEmpty()){
            note.setTitle(noteDTO.getTitle());
        }
        if (!noteDTO.getContent().isEmpty()) {
            note.setContent(noteDTO.getContent());
        }
        note.setUpdatedAt(new Date());
        Note newNote = noteRepository.save(note);
        return new NoteDTO(newNote.getTitle(), newNote.getContent(), newNote.getCreatedAt(),
                newNote.getUpdatedAt(), newNote.getId());
   }

   public ResponseEntity<String> deleteSpecificNote(long id){
        Note note = noteRepository.findByIdAndUser(id, getCurrentUser());
        if(note!=null){
            noteRepository.deleteById(id);
            return new ResponseEntity<>("Deleted the note",HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>("Note not found!",HttpStatus.NOT_FOUND);
        }


   }

   public ResponseEntity<String> deleteAllNotes(){
        List<Note> allNotes = noteRepository.findByUser(getCurrentUser());
        allNotes.forEach(singleNote -> noteRepository.deleteById(singleNote.getId()));
        return new ResponseEntity<>("All notes are deleted!", HttpStatus.NO_CONTENT);
   }

}
