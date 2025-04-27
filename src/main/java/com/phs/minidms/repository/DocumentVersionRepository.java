package com.phs.minidms.repository;

import com.phs.minidms.domain.Document;
import com.phs.minidms.domain.DocumentVersion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DocumentVersionRepository extends CrudRepository<DocumentVersion, Long> {
    List<DocumentVersion> findByDocument(Document document);
}
