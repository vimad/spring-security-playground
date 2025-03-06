package com.example.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity
public class AppConfig {

    private final SecureResourcePermissionEvaluator secureResourcePermissionEvaluator;

    public AppConfig(SecureResourcePermissionEvaluator secureResourcePermissionEvaluator) {
        this.secureResourcePermissionEvaluator = secureResourcePermissionEvaluator;
    }

    @Bean
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();

        expressionHandler.setPermissionEvaluator(secureResourcePermissionEvaluator);

        return expressionHandler;
    }
}
