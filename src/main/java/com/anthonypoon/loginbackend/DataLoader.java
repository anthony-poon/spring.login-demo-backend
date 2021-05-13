package com.anthonypoon.loginbackend;

import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader {
    private final AppUserRepository userRepo;
    private final PasswordEncoder encoder;
    @Autowired
    private DataLoader(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = appUserRepository;
        this.encoder = passwordEncoder;
        AppUser admin = loadAdmin();
        AppUser user = loadUser();
    }

    private AppUser loadUser() {
        AppUser user = AppUser.builder()
                .roles(Collections.singleton("ROLE_USER"))
                .username("user")
                .password(encoder.encode("password"))
                .build();
        userRepo.saveAndFlush(user);
        return user;
    }

    private AppUser loadAdmin() {
        AppUser admin = AppUser.builder()
                .roles(Collections.singleton("ROLE_ADMIN"))
                .username("admin")
                .password(encoder.encode("password"))
                .build();
        userRepo.saveAndFlush(admin);
        return admin;
    }
}
