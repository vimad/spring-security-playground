package com.example.authorization.controller;

import com.example.authorization.model.SecureResource;
import com.example.authorization.repository.SecureResourceRepository;
import com.example.authorization.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@Slf4j
public class ResourceController {

    private final ResourceService resourceService;
    private final SecureResourceRepository secureResourceRepository;

    public ResourceController(ResourceService greetingService, SecureResourceRepository secureResourceRepository) {
        this.resourceService = greetingService;
        this.secureResourceRepository = secureResourceRepository;
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

    @GetMapping("/secure-resource/pre-filter")
    public List<SecureResource> secureResourcePreFilter() {
        ArrayList<SecureResource> resources = new ArrayList<>();
        resources.add(new SecureResource("id1", "read", "read-user"));
        resources.add(new SecureResource("id2", "write", "write-user"));
        resources.add(new SecureResource("id3", "admin", "admin-user"));
        return resourceService.getSecureResourcePreFilter(resources);
    }

    @GetMapping("/secure-resource/post-filter")
    public List<SecureResource> secureResourcePostFilter() {
        return resourceService.getSecureResourcePostFilter();
    }

    @GetMapping ("/secure-resources")
    public List<SecureResource> getSecureResources() {
        return secureResourceRepository.findAllForOwner();
    }
}
