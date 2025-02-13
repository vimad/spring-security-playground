package com.example.authentication.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class PasswordAuthentication implements Authentication {

    private final String password;
    private boolean authenticated = false;

    private PasswordAuthentication(String password) {
        this.password = password;
    }

    public static PasswordAuthentication unauthenticated(String password) {
        return new PasswordAuthentication(password);
    }

    public static PasswordAuthentication authenticated(String password) {
        PasswordAuthentication passwordAuthentication = new PasswordAuthentication(password);
        passwordAuthentication.setAuthenticated(true);
        return passwordAuthentication;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return "KNOWN_USER";
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return getPrincipal().toString();
    }
}
