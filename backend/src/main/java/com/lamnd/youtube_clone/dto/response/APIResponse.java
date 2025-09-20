package com.lamnd.youtube_clone.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class APIResponse<T>{
    private boolean success;
    private String code;
    private String message;
    private T data;
    private String timestamp;

    public static <T> APIResponse<T> success(T data, String code, String message) {
        return APIResponse.<T>builder()
                .success(true)
                .code(code != null ? code : "OK")
                .message(message != null ? message : "success")
                .data(data)
                .timestamp(Instant.now().toString())
                .build();
    }

    public static <T> APIResponse<T> error(String code, String message, T data) {
        return APIResponse.<T>builder()
                .success(false)
                .code(code != null ? code : "ERROR")
                .message(message != null ? message : "error")
                .data(data)
                .timestamp(Instant.now().toString())
                .build();
    }
}
