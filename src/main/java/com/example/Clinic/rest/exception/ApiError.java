package com.example.Clinic.rest.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {

    private HttpStatus status;
    private String code;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<String> details;

    public ApiError(HttpStatus status, LocalDateTime timestamp, String message, List<String> details) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}

