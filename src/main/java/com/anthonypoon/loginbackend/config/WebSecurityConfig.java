package com.anthonypoon.loginbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                // .antMatchers("/demo/path-1").hasAnyRole("USER", "ADMIN")
                // .antMatchers("/demo/path-2").hasAnyRole("USER", "ADMIN")
                .anyRequest().permitAll()
            .and()
                .httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user);
    }
}

// Annotation:
// - @Configuration + @Bean creates a bean for other class to use
// - In this context is, WebSecurityConfig took over the creation of UserDetailsService and configuration of HttpSecurity.
// - Other class will use InMemoryUserDetailsManager we created

// sessionManagement:
// - Tell spring that we need to re-authenticate on every call
// - Can demo by switching auth header on and off in Postman

// antMatchers:
// - Match the URL to the patterns, and see what security policy to apply
// - Ordering from top to bottom, other rules is ignored if one is matched
// - hasRole()      =>  user must have all roles
// - hasAnyRole()   =>  user have any of roles
// - permitAll()    =>  user with or without login
// - anonymous      =>  user without login only