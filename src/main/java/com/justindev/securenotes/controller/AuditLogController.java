package com.justindev.securenotes.controller;

import com.justindev.securenotes.model.AuditLog;
import com.justindev.securenotes.service.IAuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("audit")
@RequiredArgsConstructor
public class AuditLogController {

    private final IAuditLogService auditLogService;

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllAuditLogs() {
        return new ResponseEntity<>(this.auditLogService.getAllAuditLogs(), HttpStatus.OK);
    }

    @GetMapping("note/{noteId}")
    public ResponseEntity<List<AuditLog>> getAllAuditLogsByNoteId(@PathVariable Long noteId) {
        return new ResponseEntity<>(this.auditLogService.getAllAuditLogsByNoteId(noteId), HttpStatus.OK);
    }

}
