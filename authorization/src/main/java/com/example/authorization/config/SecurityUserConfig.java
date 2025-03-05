package com.example.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
public class SecurityUserConfig {

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails readUser = User.withUsername("read-user")
            .password("123")
            .authorities("read")
            .build();
        UserDetails writeUser = User.withUsername("write-user")
            .password("456")
            .authorities(new SimpleGrantedAuthority("WRITE"))
            .build();
        UserDetails readWriteUser = User.withUsername("read-write-user")
            .password("789")
            .authorities("read", "write")
            .build();
        UserDetails adminUser = User.withUsername("admin-user")
            .password("789")
            .authorities("ROLE_admin")
            .build();
        return new InMemoryUserDetailsManager(List.of(readUser, writeUser, readWriteUser, adminUser));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
