package com.justindev.securenotes.service.impl;

import com.justindev.securenotes.model.AuditLog;
import com.justindev.securenotes.model.Note;
import com.justindev.securenotes.repository.IAuditLogRepository;
import com.justindev.securenotes.service.IAuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuditLogService implements IAuditLogService {

    private final IAuditLogRepository auditLogRepository;

    @Override
    public void logNoteCreation(String username, Note note) {
        log(username, note, "CREATE");
    }

    @Override
    public void logNoteUpdate(String username, Note note) {
        log(username, note, "UPDATE");
    }

    @Override
    public void logNoteDeletion(String username, Note note) {
        log(username, note, "DELETE");
    }

    private void log(String username, Note note, String action) {
        AuditLog auditLog = AuditLog.builder()
                .action(action)
                .username(username)
                .noteId(note.getNoteId())
                .noteContent(note.getContent())
                .timestamp(LocalDateTime.now())
                .build();
        this.auditLogRepository.save(auditLog);
    }

    @Override
    public List<AuditLog> getAllAuditLogs() {
        return this.auditLogRepository.findAll();
    }

    @Override
    public List<AuditLog> getAllAuditLogsByNoteId(Long noteId) {
        return this.auditLogRepository.findByNoteId(noteId);
    }
}
