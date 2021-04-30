package com.anthonypoon.loginbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and().httpBasic();
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

// Review on JPA basic:
// You need a entity, repository and a service layer
// - Entity         =>  a row in a database, or POJO representation of a record in database
// - Repository     =>  Class that interact with database
// - Service Layer  =>  Perform logical operation

// Change in-memory user service to database user service
// Step 1: Setup AppUser entity, AppUserRepository, AppUserDetailService
// Step 2: Create a DataLoader to add first user on load
// Step 3: Add a PasswordEncoder bean in WebSecurityConfig
// Step 4: Implement UserDetails in AppUser -> Implementing UserDetails enables other spring service to use