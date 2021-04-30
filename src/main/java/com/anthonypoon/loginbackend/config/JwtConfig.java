package com.anthonypoon.loginbackend.config;

import com.anthonypoon.loginbackend.services.JwtTokenFilter;
import com.anthonypoon.loginbackend.services.JwtTokenProvider;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenFilter filter;

    public JwtConfig(JwtTokenFilter filter) {
        this.filter = filter;
    }

    @Override
    public void configure(HttpSecurity http) {
        http.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
    }
}
