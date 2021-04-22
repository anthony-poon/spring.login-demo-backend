package com.anthonypoon.loginbackend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HeartBeatResponseDTO {
    private LocalDateTime timestamp = LocalDateTime.now();
}
