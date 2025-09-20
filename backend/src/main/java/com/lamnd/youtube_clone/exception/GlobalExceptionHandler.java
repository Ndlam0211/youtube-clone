package com.lamnd.youtube_clone.exception;

import com.lamnd.youtube_clone.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<Object>> handleNotFound(ResourceNotFoundException ex, WebRequest request) {
        return new ResponseEntity<>(APIResponse.error("404", ex.getMessage(), null), HttpStatus.NOT_FOUND);
    }

    private Map<String, Object> meta(WebRequest request, List<Map<String, Object>> errors) {
        Map<String, Object> body = new HashMap<>();
        body.put("path", request.getDescription(false).replace("uri=", ""));
        if (errors != null) body.put("errors", errors);
        return body;
    }
}
