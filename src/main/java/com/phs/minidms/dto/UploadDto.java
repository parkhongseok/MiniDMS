package com.phs.minidms.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadDto {
    private String description;
    private MultipartFile file;
}
