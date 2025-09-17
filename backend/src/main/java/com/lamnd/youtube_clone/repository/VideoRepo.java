package com.lamnd.youtube_clone.repository;

import com.lamnd.youtube_clone.entity.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepo extends MongoRepository<Video,String> {
}
