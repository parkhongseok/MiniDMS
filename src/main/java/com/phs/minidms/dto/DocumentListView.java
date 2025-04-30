package com.phs.minidms.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public interface DocumentListView {

    // Projection 필드 (Spring Data가 자동으로 바인딩)
    String getFilename();
    String getDescription();
    String getFilePath();
    Long getFileSize();
    Integer getVersionNumber();
    String getUploader();
    LocalDateTime getUploadedAt();

    // null-safe wrapper
    default String getSafeDescription() {
        return getDescription() != null ? getDescription() : "(설명 없음)";
    }

    default String getSafeUploader() {
        return getUploader() != null ? getUploader() : "미등록 사용자";
    }

    default String getSafeFilename() {
        return getFilename() != null ? getFilename() : "이름 없음";
    }

    default String getDisplayFilesize() {
        if (getFileSize() == null || getFileSize() < 1024) {
            return getFileSize() + " B";
        } else {
            return String.format("%.1f KB", getFileSize() / 1024.0);
        }
    }
    default String getFormattedDate() {
        if (getUploadedAt() == null) return "";
        return getUploadedAt().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
