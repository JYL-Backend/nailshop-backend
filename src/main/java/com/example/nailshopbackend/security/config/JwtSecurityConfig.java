package com.example.nailshopbackend.security.config;

import com.example.nailshopbackend.security.filter.JwtExceptionFilter;
import com.example.nailshopbackend.security.filter.JwtFilter;
import com.example.nailshopbackend.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   private final TokenProvider tokenProvider;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        JwtExceptionFilter jwtExceptionFilter = new JwtExceptionFilter();
        JwtFilter customFilter= new JwtFilter(tokenProvider);
        // JWT 필터
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        // JWT 필터 등록 전 JWT Exception 필터 등록
        http.addFilterBefore(jwtExceptionFilter, JwtFilter.class);
    }
}
