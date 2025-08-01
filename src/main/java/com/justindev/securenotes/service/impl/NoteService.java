package com.justindev.securenotes.service.impl;

import com.justindev.securenotes.model.Note;
import com.justindev.securenotes.repository.INoteRepository;
import com.justindev.securenotes.service.INoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService implements INoteService {

    private final INoteRepository noteRepository;

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        return this.noteRepository.save(note);
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = this.noteRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        if (!note.getOwnerUsername().equals(username)) {
            throw new RuntimeException("You do not have permission to update this note");
        }
        note.setContent(content);
        return noteRepository.save(note);
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        this.noteRepository.deleteById(noteId);
    }

    @Override
    public List<Note> getNotesForUser(String username) {
        return this.noteRepository.findByOwnerUsername(username);
    }
}
