package com.phs.minidms.service;

import com.phs.minidms.domain.Document;
import com.phs.minidms.domain.DocumentVersion;
import com.phs.minidms.dto.DocumentListView;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentVersionRepository documentVersionRepository;
    private final FileUtil fileUtil;

    public List<DocumentListView> findAllView() {
        return documentRepository.findAllView();
    }


    @Transactional
    public Document saveDocumentWithFile(UploadDto uploadDto) {
        MultipartFile file = uploadDto.getFile();

        try {
            // 1. 파일 저장
            File savedFile = fileUtil.saveFile(file);


            // 2. Document 저장
            Document document = Document.builder()
                    .createdAt(LocalDateTime.now())
                    .createdBy(null) // TODO: 로그인 사용자로 교체
                    .build();

            documentRepository.save(document);

            // 3. versionNumber 결정: 현재 문서의 기존 버전 수를 기준으로 계산
            int versionNumber = 1; // 새 문서이므로 1로 고정

            DocumentVersion version = new DocumentVersion();
            version.setDocument(document);
            version.setDescription(uploadDto.getDescription());
            version.setVersionNumber(versionNumber);
            version.setOriginalFilename(file.getOriginalFilename()); // 원본 파일명
            version.setSavedFilename(savedFile.getName()); // 저장된 파일명 (UUID)
            version.setFilePath(fileUtil.getRelativePath(savedFile));
            version.setFileSize(file.getSize());
            version.setUploadedAt(LocalDateTime.now());
            version.setUploadedBy(null); // TODO: 로그인 사용자로 교체

            documentVersionRepository.save(version);

            // 4. 최신 버전 지정
            document.setCurrentDocumentVersion(version);

            // 5. 버전 정보가 반영된, 최신의 Document 객체 리턴
            return  documentRepository.save(document);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 실패", e);
        }
    }
}
