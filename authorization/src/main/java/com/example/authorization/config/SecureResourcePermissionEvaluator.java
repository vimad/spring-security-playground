package com.example.authorization.config;

import com.example.authorization.model.SecureResource;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SecureResourcePermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(
        Authentication authentication,
        Object target,
        Object permission) {

        SecureResource secureResource = (SecureResource) target;
        String p = (String) permission;

        boolean hasPermission =
            authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(p));

        return hasPermission && secureResource.getOwner().equals(authentication.getName());

    }

    @Override
    public boolean hasPermission(Authentication authentication,
                                 Serializable targetId,
                                 String targetType,
                                 Object permission) {
        return false;
    }
}
