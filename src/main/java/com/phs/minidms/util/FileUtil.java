package com.phs.minidms.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class FileUtil {
    // Mac/Win 모두 호환 가능하게 설정 TEST 용 URL
    private final String uploadRoot = System.getProperty("user.home") + "/minidms/uploads";

    public File saveFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("업로드된 파일이 없습니다.");
        }

        // 오늘 날짜로 폴더 구조 생성
        LocalDate today = LocalDate.now();
        String dirPath = String.format("%s/%d/%02d/%02d", uploadRoot,
                today.getYear(), today.getMonthValue(), today.getDayOfMonth());

        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // 중복 방지를 위한 파일명 UUID 적용
        String originalFilename = multipartFile.getOriginalFilename();
        String extension = getFileExtension(originalFilename);
        String uniqueName = UUID.randomUUID() + (extension.isEmpty() ? "" : "." + extension);

        File savedFile = new File(dir, uniqueName);
        multipartFile.transferTo(savedFile);

        System.out.println("📁 저장된 파일: " + savedFile.getAbsolutePath());
        return savedFile;
    }

    public String getFileExtension(String filename) {
        if (filename == null) return "";
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex >= 0) ? filename.substring(dotIndex + 1) : "";
    }

    public String getRelativePath(File file) {
        return file.getAbsolutePath().replace(uploadRoot, "");
    }
}
