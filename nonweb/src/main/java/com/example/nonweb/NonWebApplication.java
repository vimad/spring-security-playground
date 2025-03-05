package com.example.nonweb;

import com.example.nonweb.service.SecureService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Scanner;

@SpringBootApplication
public class NonWebApplication implements CommandLineRunner {

    private final AuthenticationManager authenticationManager;
    private final SecureService secureService;

    public NonWebApplication(AuthenticationManager authenticationManager, SecureService secureService) {
        this.authenticationManager = authenticationManager;
        this.secureService = secureService;
    }

    public static void main(String[] args) {
        SpringApplication.run(NonWebApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            // Authenticate user input
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);

            System.out.println("✅ Authentication successful for user: " + auth.getName());
            System.out.println("🔹 Roles: " + auth.getAuthorities());

            // Call secure service method after successful authentication
            secureService.performSecureOperation();

        } catch (Exception e) {
            System.out.println("❌ Authentication failed: " + e.getMessage());
        }

        scanner.close();
    }

}
