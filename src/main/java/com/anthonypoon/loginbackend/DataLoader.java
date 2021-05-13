package com.anthonypoon.loginbackend;

import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader {
    @Autowired
    private DataLoader(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        AppUser admin = AppUser.builder()
                .roles(Collections.singleton("ROLE_ADMIN"))
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .build();
        appUserRepository.saveAndFlush(admin);
        AppUser user = AppUser.builder()
                .roles(Collections.singleton("ROLE_USER"))
                .username("user")
                .password(passwordEncoder.encode("password"))
                .build();
        appUserRepository.saveAndFlush(user);
    }
}
