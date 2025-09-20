package com.lamnd.youtube_clone.service.impl;

import com.lamnd.youtube_clone.dto.request.UpdateVideoRequest;
import com.lamnd.youtube_clone.dto.response.VideoUploadResponse;
import com.lamnd.youtube_clone.entity.Video;
import com.lamnd.youtube_clone.exception.ResourceNotFoundException;
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
    public VideoUploadResponse uploadVideo(MultipartFile file) {
        // Upload file to AWS S3 and get the url
        String videoUrl = s3Service.uploadFile(file);

        // Create new video object
        var video = new Video();
        video.setVideoUrl(videoUrl);

        // Save video metadata to MongoDB
        var savedVideo = videoRepo.save(video);

        // Return response
        return VideoUploadResponse.builder()
                .videoId(savedVideo.getId())
                .videoUrl(videoUrl)
                .build();
    }

    @Override
    public Video updateVideoMetadata(String id, UpdateVideoRequest request) {
        // Get video by id
        var savedVideo = getVideoById(id);

        // Map updated request to saved video
        savedVideo.setTitle(request.getTitle());
        savedVideo.setDescription(request.getDescription());
        savedVideo.setTags(request.getTags());
        savedVideo.setThumbnailUrl(request.getThumbnailUrl());
        savedVideo.setVideoStatus(request.getVideoStatus());

        // Save updated video to MongoDB
        return videoRepo.save(savedVideo);
    }

    @Override
    public String uploadThumbnail(MultipartFile file, String videoId) {
        // Get video by id
        var savedVideo = getVideoById(videoId);

        // Upload thumbnail file to AWS S3 and get the url
        String thumbnailUrl = s3Service.uploadFile(file);

        // Update video thumbnail url
        savedVideo.setThumbnailUrl(thumbnailUrl);

        // Save updated video to MongoDB
        videoRepo.save(savedVideo);

        return thumbnailUrl;
    }

    private Video getVideoById(String videoId) {
        return videoRepo.findById(videoId)
                .orElseThrow(() -> new ResourceNotFoundException("Video not found with id: " + videoId));
    }
}
