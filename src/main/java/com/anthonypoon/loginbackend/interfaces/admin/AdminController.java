package com.anthonypoon.loginbackend.interfaces.admin;

import com.anthonypoon.loginbackend.dto.HeartBeatResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/heart-beat")
    public HeartBeatResponseDTO getHeartBeat() {
        return new HeartBeatResponseDTO();
    }
}

// Add in-memory user service + HTTP Basic authentication
// Step 1: Add spring-boot-starter-security as dependency
// Step 2: ***.config.WebSecurityConfig (extend WebSecurityConfigurerAdapter)

// Change in-memory user service to database user service
// Step 1: Setup AppUser entity, AppUserRepository, AppUserDetailService
// Step 2: Create a DataLoader to add first user on load
// Step 3: Add a PasswordEncoder bean in WebSecurityConfig
// Step 4: Implement UserDetails in AppUser
