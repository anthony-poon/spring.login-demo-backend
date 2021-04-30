package com.anthonypoon.loginbackend.services;

import com.anthonypoon.loginbackend.models.AppUser;
import com.anthonypoon.loginbackend.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailService implements UserDetailsService {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public AppUser tryUsernamePassword(String username, String password) {
        AppUser user = appUserRepository.findByUsername(username);
        if (user == null) {
            return null;
        }
        String hash = user.getPassword();
        if (!this.encoder.matches(password, hash)) {
            return null;
        }
        return user;
    }
}
