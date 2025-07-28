package com.jobseek.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;

@Data
//@AllArgsConstructor
public class ApiResponse {
    private String message;
    public ApiResponse(String message) {
        this.message = message;
        LocalDateTime timestamp = LocalDateTime.now();
    }

}
