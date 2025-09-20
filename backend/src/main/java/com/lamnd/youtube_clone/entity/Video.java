package com.lamnd.youtube_clone.entity;

import com.lamnd.youtube_clone.enums.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "videos")
public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private Integer likes;
    private Integer dislikes;
    private Integer viewCount;
    private Set<String> tags;
    private String videoUrl;
    private String thumbnailUrl;
    private VideoStatus videoStatus;
    private List<Comment> comments;
}
