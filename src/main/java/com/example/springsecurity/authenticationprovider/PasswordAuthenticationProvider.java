package com.example.springsecurity.authenticationprovider;

import com.example.springsecurity.config.PasswordAuthentication;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

public class PasswordAuthenticationProvider implements AuthenticationProvider {

    private final List<String> passwords;

    public PasswordAuthenticationProvider(List<String> passwords) {
        this.passwords = passwords;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (passwords.contains(authentication.getCredentials().toString())) {
            // return UsernamePasswordAuthenticationToken.authenticated(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
            return PasswordAuthentication.authenticated(authentication.getCredentials().toString());
        } else {
            throw new BadCredentialsException("Something went wrong!");
            // return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PasswordAuthentication.class.isAssignableFrom(authentication);
        // return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
