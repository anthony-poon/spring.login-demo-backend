package com.anthonypoon.loginbackend.services;

import com.anthonypoon.loginbackend.config.JwtConfig;
import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.repos.AppUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JwtTokenProvider generate JWT from user authentication, or validate
 *
 * https://medium.com/@xoor/jwt-authentication-service-44658409e12c
 */
@Component
public class JwtTokenProvider {

    private final JWTVerifier verifier;
    private final Algorithm algorithm;
    private final AppUserRepository userRepo;

    public JwtTokenProvider(AppUserRepository userRepo, @Value("${spring.jwt.secret}") String secret) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(this.algorithm)
                .build();
        this.userRepo = userRepo;
    }

    public AppUser decodeJwt(String token) {
        try {
            if (token == null || token.isEmpty()) {
                return null;
            }
            DecodedJWT decoded = this.verifier.verify(token);
            // Might be a vulnerability. Exposed username
            String username = decoded.getClaim("username").asString();
            return this.userRepo.findByUsername(username);
        } catch (JWTVerificationException exception) {
            return null;
        }
    }

    public String encodeJwt(AppUser user) {
        List<String> authorities = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return JWT.create()
                .withClaim("username", user.getUsername())
                .withClaim("authorities", authorities)
                .sign(this.algorithm);
    }
}
