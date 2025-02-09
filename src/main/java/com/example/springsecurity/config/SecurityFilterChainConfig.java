package com.example.springsecurity.config;

import com.example.springsecurity.filter.AuthenticationLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;

    public SecurityFilterChainConfig(AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .authenticationProvider(authenticationProvider)
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Ensure authentication is required
            )
            .with(new PasswordLoginConfigurer(), configurer -> {
                configurer.password("secret1");
                configurer.password("secret2");
            })
            .formLogin(configurer -> {
                configurer.defaultSuccessUrl("/greeting", true);
            })
            .httpBasic(configurer -> {
                configurer.realmName("custom");
            })
            .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class)
            .build();
    }
}
