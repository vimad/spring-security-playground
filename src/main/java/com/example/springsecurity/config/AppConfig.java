package com.example.springsecurity.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableAsync
@Slf4j
public class AppConfig {
    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder
            .setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }

    @Bean
    public ApplicationListener<AuthenticationSuccessEvent> authenticationSuccessListener() {
        return event -> {
            log.info("Authentication success with user {}", event.getAuthentication().getName());
        };
    }
}
