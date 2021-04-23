package com.anthonypoon.loginbackend.services;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final static Pattern p = Pattern.compile("^Bearer (.+)");

    private final JwtTokenProvider provider;

    public JwtTokenFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        // 1. Get token from request header
        String jwt = getTokenFromRequest(req);
        if (jwt == null || jwt.isEmpty()) {
            chain.doFilter(req, res);
            return;
        }
        UserDetails user = loadUserFromToken(jwt);
        if (user == null) {
            chain.doFilter(req, res);
            return;
        }
        Authentication auth = buildAuthentication(user);
        SecurityContextHolder.getContext().setAuthentication(auth);
        chain.doFilter(req, res);
    }

    private String getTokenFromRequest(HttpServletRequest req) {
        // Format header is Authorization: Bearer [JWT]
        // Thus we need to get the header value and remove the "Bearer " parts
        String value = req.getHeader(HttpHeaders.AUTHORIZATION);
        if (value == null || value.isEmpty()) {
            return null;
        }
        Matcher m = p.matcher(value);
        if (m.matches()) {
            return m.group(1);
        }
        return null;
    }

    private UserDetails loadUserFromToken(String jwt) {
        return provider.decodeJwt(jwt);
    }

    private Authentication buildAuthentication(UserDetails user) {
        return new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities());
    }
}
