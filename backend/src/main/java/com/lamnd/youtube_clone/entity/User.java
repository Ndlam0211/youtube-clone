package com.lamnd.youtube_clone.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private Set<String> subscribedToUsers; // IDs of users this user is subscribed to
    private Set<String> subscribers; // IDs of users who are subscribed to this user
    private Set<String> likedVideos; // IDs of videos this user has liked
    private Set<String> dislikedVideos; // IDs of videos this user has disliked
    private List<String> videoHistory; // IDs of videos this user has watched
}
