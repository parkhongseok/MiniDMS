package com.phs.minidms.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "document_versions")
public class DocumentVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id")
    private Document document;


    private String originalFilename; // 실제 업로드한 파일 이름
    private String savedFilename; // 저장된 파일명 - UUID로 변환된 실제 저장 파일명
    private String description; // 선택적으로 문서 설명
    private String filePath;
    private int versionNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by")
    private Member uploadedBy;

    private long fileSize;

    private LocalDateTime uploadedAt;

}
