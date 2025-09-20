package com.lamnd.youtube_clone.service;

import com.lamnd.youtube_clone.dto.request.UpdateVideoRequest;
import com.lamnd.youtube_clone.entity.Video;
import org.springframework.web.multipart.MultipartFile;

public interface IVideoService {
    void uploadVideo(MultipartFile file);

    Video updateVideoMetadata(String id, UpdateVideoRequest request);

    String uploadThumbnail(MultipartFile file, String videoId);
}
