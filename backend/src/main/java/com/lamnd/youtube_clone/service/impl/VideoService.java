package com.lamnd.youtube_clone.service.impl;

import com.lamnd.youtube_clone.entity.Video;
import com.lamnd.youtube_clone.repository.VideoRepo;
import com.lamnd.youtube_clone.service.IVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService implements IVideoService {
    private final S3Service s3Service;

    private final VideoRepo videoRepo;

    @Override
    public void uploadVideo(MultipartFile file) {
        // Upload file to AWS S3 and get the url
        String videoUrl = s3Service.uploadFile(file);

        // Create new video object
        var video = new Video();
        video.setVideoUrl(videoUrl);

        // Save video metadata to MongoDB
        videoRepo.save(video);
    }
}
