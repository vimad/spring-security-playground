package com.example.authentication.config;

import com.example.authentication.authenticationprovider.PasswordAuthenticationProvider;
import com.example.authentication.filter.PasswordHeaderFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

public class PasswordLoginConfigurer extends AbstractHttpConfigurer<PasswordLoginConfigurer, HttpSecurity> {

    private final List<String> passwords = new ArrayList<>();

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.authenticationProvider(new PasswordAuthenticationProvider(passwords));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        var authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterBefore(new PasswordHeaderFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
    }

    public void password(String password) {
        this.passwords.add(password);
    }
}
