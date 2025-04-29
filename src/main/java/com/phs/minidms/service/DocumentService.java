package com.phs.minidms.service;

import com.phs.minidms.domain.Document;
import com.phs.minidms.domain.DocumentVersion;
import com.phs.minidms.dto.UploadDto;
import com.phs.minidms.repository.DocumentRepository;
import com.phs.minidms.repository.DocumentVersionRepository;
import com.phs.minidms.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;
    private final FileUtil fileUtil;

    @Transactional
    public void saveDocumentWithFile(UploadDto uploadDto) {
        MultipartFile file = uploadDto.getFile();

        try {
            // 1. 파일 저장
            File savedFile = fileUtil.saveFile(file);

            // 2. Document 저장
            Document document = Document.builder()
                    .title(uploadDto.getTitle())
                    .description(uploadDto.getDescription())
                    .createdAt(LocalDateTime.now())
                    .createdBy(null) // TODO: 로그인 사용자로 교체
                    .build();

            documentRepository.save(document);

            // 3. versionNumber 결정: 현재 문서의 기존 버전 수를 기준으로 계산
            int versionNumber = 1; // 새 문서이므로 1로 고정

            DocumentVersion version = new DocumentVersion();
            version.setDocument(document);
            version.setVersionNumber(versionNumber);
            version.setFilename(savedFile.getName());
            version.setFilePath(fileUtil.getRelativePath(savedFile));
            version.setFileSize(file.getSize());
            version.setUploadedAt(LocalDateTime.now());
            version.setUploadedBy(null); // TODO: 로그인 사용자로 교체

            documentVersionRepository.save(version);

            // 4. 최신 버전 지정
            document.setCurrentDocumentVersion(version);

        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }
}
