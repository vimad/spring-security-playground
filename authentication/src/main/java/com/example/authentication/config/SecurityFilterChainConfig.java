package com.example.authentication.config;

import com.example.authentication.authenticationprovider.SuperUserAuthenticationProvider;
import com.example.authentication.filter.AuthenticationLoggingFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
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
        httpSecurity
            .authenticationProvider(authenticationProvider)
            .authenticationProvider(new SuperUserAuthenticationProvider())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated() // Ensure authentication is required
            )
            .with(
                new PasswordLoginConfigurer(), configurer -> {
                    configurer.password("secret1");
                    configurer.password("secret2");
                }
            )
            .formLogin(configurer -> {
                configurer.defaultSuccessUrl("/greeting", true);
            })
            .httpBasic(configurer -> {
                configurer.realmName("custom");
            })
            .addFilterAfter(new AuthenticationLoggingFilter(), BasicAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
