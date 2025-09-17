package com.lamnd.youtube_clone.service;

import org.springframework.web.multipart.MultipartFile;

public interface IVideoService {
    void uploadVideo(MultipartFile file);
}
