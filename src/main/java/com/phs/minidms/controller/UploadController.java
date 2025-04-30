package com.phs.minidms.controller;

import com.phs.minidms.domain.Document;
import com.phs.minidms.dto.UploadDto;
import com.phs.minidms.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UploadController {
    private final DocumentService documentService;

    @GetMapping("/upload")
    public String showUploadForm() {
        return "upload"; // /WEB-INF/views/upload.jsp
    }

    @PostMapping("/upload")
    public String handleUpload(@ModelAttribute UploadDto uploadDto, Model model) {
        Document document = documentService.saveDocumentWithFile(uploadDto);

        String savedPath = document.getCurrentVersion().getFilePath();
        model.addAttribute("savedPath", savedPath);

        return "upload-result"; // 업로드 후 목록 페이지로 리다이렉트
    }
}
