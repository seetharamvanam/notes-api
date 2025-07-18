package com.notesapi.notes_api.repository;

import com.notesapi.notes_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
        User findByEmailAddress(String email);
}
