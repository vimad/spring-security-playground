package com.example.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityFilterChainConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/read").hasAuthority("read")
                .requestMatchers("/write").access(
                    new WebExpressionAuthorizationManager("hasAuthority('write')")
                )
                .requestMatchers("/read-write").access(
                    new WebExpressionAuthorizationManager("hasAuthority('write') and hasAuthority('read')")
                )
                .requestMatchers("/admin").hasRole("admin")
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
