package com.phs.minidms.controller;

import com.phs.minidms.domain.Document;
import com.phs.minidms.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TestController {
    private final DocumentRepository documentRepository;

    @GetMapping("/test-create")
    @ResponseBody
    public String testCreate() {
        Document document = new Document();
        document.setTitle("Test Document");
        document.setDescription("This is a test document.");
        document.setCreatedAt(LocalDateTime.now());
        documentRepository.save(document);
        return "Saved!";
    }

    @GetMapping("/test-list")
    @ResponseBody
    public List<Document> testList() {
        return (List<Document>) documentRepository.findAll();
    }
}