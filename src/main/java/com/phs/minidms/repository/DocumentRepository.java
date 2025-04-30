package com.phs.minidms.repository;

import com.phs.minidms.domain.Document;
import com.phs.minidms.dto.DocumentListView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("""
    SELECT 
        v.originalFilename AS filename,
        v.description AS description,
        v.filePath AS filePath,
        v.fileSize AS fileSize,
        v.versionNumber AS versionNumber,
        m.username AS uploader,
        d.createdAt AS uploadedAt
    FROM Document d
    JOIN d.currentVersion v
    LEFT JOIN d.createdBy m
    ORDER BY d.createdAt DESC
""")
    List<DocumentListView> findAllView();
}
