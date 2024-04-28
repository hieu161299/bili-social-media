package com.bilibili.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(
                management -> management.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(Authorize -> Authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable());

        return  http.build();
    }
}
