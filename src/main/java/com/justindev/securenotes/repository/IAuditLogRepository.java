package com.justindev.securenotes.repository;

import com.justindev.securenotes.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAuditLogRepository extends JpaRepository<AuditLog, Long> {

    List<AuditLog> findByNoteId(Long noteId);

}
