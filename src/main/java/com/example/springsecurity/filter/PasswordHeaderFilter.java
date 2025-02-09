package com.example.springsecurity.filter;

import com.example.springsecurity.config.PasswordAuthentication;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class PasswordHeaderFilter extends OncePerRequestFilter {

    private final AuthenticationManager authenticationManager;
    private SecurityContextRepository securityContextRepository = new RequestAttributeSecurityContextRepository();

    public PasswordHeaderFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        if (!Collections.list(request.getHeaderNames()).contains("x-password")) {
            filterChain.doFilter(request, response);
            return;
        }

        String password = request.getHeader("x-password");
        var authentication = PasswordAuthentication.unauthenticated(password);
        // var authentication = UsernamePasswordAuthenticationToken.unauthenticated("KNOWN_USER", password);
        try {
            Authentication authenticate = authenticationManager.authenticate(authentication);
            var securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticate);
            securityContextRepository.saveContext(securityContext, request, response);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write("{\"message\":\"Invalid password\"}");
        }
    }
}
