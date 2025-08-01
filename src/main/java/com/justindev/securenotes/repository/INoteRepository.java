package com.justindev.securenotes.repository;

import com.justindev.securenotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByOwnerUsername(String username);

    Note findByNoteId(Long noteId);
}
