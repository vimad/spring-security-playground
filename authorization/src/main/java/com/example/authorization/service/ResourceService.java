package com.example.authorization.service;

import com.example.authorization.model.SecureResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ResourceService {

    public String greet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Hello " + authentication.getName() + "!!!";
    }

    @PostAuthorize("hasPermission(returnObject, 'read')")
    public SecureResource getSecureResource() {
        return new SecureResource("id", "name", "read-user");
    }
}
