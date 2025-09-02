package com.justindev.securenotes.service;

import com.justindev.securenotes.model.AuditLog;
import com.justindev.securenotes.model.Note;

import java.util.List;

public interface IAuditLogService {

    void logNoteCreation(String username, Note note);

    void logNoteUpdate(String username, Note note);

    void logNoteDeletion(String username, Note note);

    List<AuditLog> getAllAuditLogs();

    List<AuditLog> getAllAuditLogsByNoteId(Long noteId);

}
