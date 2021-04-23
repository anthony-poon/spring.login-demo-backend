package com.anthonypoon.loginbackend;

import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {
    @Autowired
    private DataLoader(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        AppUser user = AppUser.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .build();
        appUserRepository.saveAndFlush(user);
    }
}
