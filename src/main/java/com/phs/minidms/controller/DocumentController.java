package com.phs.minidms.controller;

import com.phs.minidms.domain.Document;
import com.phs.minidms.dto.DocumentListView;
import com.phs.minidms.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping("/documents")
    public String listDocuments(Model model) {
        List<DocumentListView> documentsView = documentService.findAllView();

        model.addAttribute("documentList", documentsView);
        return "document-list";
    }

}

