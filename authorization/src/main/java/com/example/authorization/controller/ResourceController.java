package com.example.authorization.controller;

import com.example.authorization.model.SecureResource;
import com.example.authorization.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Slf4j
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService greetingService) {
        this.resourceService = greetingService;
    }

    @GetMapping("/read")
    public String read() {
        log.info("Read resource");
        return "read";
    }

    @GetMapping("/write")
    public String write() {
        log.info("Write resource");
        return "write";
    }

    @GetMapping("/read-write")
    public String readWrite() {
        log.info("Read-write resource");
        return "read-write";
    }

    @GetMapping("/admin")
    public String admin() {
        log.info("Admin resource");
        return "admin";
    }

    @GetMapping("/secure-resource")
    public SecureResource secureResource() {
        return resourceService.getSecureResource();
    }
}
