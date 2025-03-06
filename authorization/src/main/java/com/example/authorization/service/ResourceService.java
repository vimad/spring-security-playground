package com.example.authorization.service;

import com.example.authorization.model.SecureResource;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @PreFilter("filterObject.owner == authentication.name")
    public List<SecureResource> getSecureResourcePreFilter(List<SecureResource> secureResources) {
        return secureResources;
    }

    @PostFilter("filterObject.owner == authentication.name")
    public List<SecureResource> getSecureResourcePostFilter() {
        ArrayList<SecureResource> resources = new ArrayList<>();
        resources.add(new SecureResource("id1", "read", "read-user"));
        resources.add(new SecureResource("id2", "write", "write-user"));
        resources.add(new SecureResource("id3", "admin", "admin-user"));
        return resources;
    }
}
