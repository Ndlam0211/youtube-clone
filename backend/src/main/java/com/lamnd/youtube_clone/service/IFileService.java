package com.lamnd.youtube_clone.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IFileService {
    String uploadFile(MultipartFile file);
}
