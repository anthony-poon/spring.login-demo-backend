package com.anthonypoon.loginbackend.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class JwtConfig {
    @Value("${spring.jwt.secret}")
    private String jwtSecret;
}
