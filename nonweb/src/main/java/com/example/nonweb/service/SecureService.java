package com.example.nonweb.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class SecureService {

    @PreAuthorize("hasRole('USER')")
    public void performSecureOperation() {
        System.out.println("🔐 Secure operation executed! You have access.");
    }
}
