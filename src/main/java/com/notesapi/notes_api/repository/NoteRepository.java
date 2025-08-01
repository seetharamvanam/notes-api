package com.notesapi.notes_api.repository;

import com.notesapi.notes_api.model.Note;
import com.notesapi.notes_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByUser(User user);
    Note findByIdAndUser(Long aLong, User user);
}
