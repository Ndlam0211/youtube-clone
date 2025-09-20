package com.lamnd.youtube_clone.controller;

import com.lamnd.youtube_clone.dto.request.UpdateVideoRequest;
import com.lamnd.youtube_clone.dto.response.VideoUploadResponse;
import com.lamnd.youtube_clone.entity.Video;
import com.lamnd.youtube_clone.service.IVideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
    private final IVideoService videoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VideoUploadResponse uploadVideo(@RequestParam("file") MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    @PostMapping("/thumbnail")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId) {
        return videoService.uploadThumbnail(file, videoId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Video updateVideoMetadata(@PathVariable String id, @RequestBody UpdateVideoRequest request) {
        return videoService.updateVideoMetadata(id, request);
    }
}
