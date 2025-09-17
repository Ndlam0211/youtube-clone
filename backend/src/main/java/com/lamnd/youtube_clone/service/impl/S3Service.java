package com.lamnd.youtube_clone.service.impl;

import com.lamnd.youtube_clone.config.S3Config;
import com.lamnd.youtube_clone.service.IFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements IFileService {
    private final S3Client s3Client;
    private final S3Config s3Config;

    @Override
    public String uploadFile(MultipartFile file) {
        // Create a unique key
        var filenameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        var key = UUID.randomUUID().toString() + (filenameExtension != null ? "." + filenameExtension : "");

        // Create metadata
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Config.getS3BucketName())
                .key(key)
                .contentType(file.getContentType())
                .build();

        // Upload file to AWS S3
        try {
            s3Client.putObject(
                    putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        } catch (IOException ioException) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An exception occurred while uploading the file: " + ioException.getMessage());
        }

        // return the S3 file URL
        return String.format("https://%s.s3.%s.amazonaws.com/%s",
                s3Config.getS3BucketName(), s3Config.getAwsRegion(), key);
    }
}
