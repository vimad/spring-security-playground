package com.example.springsecurity.controller;

import com.example.springsecurity.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/greeting")
@Slf4j
public class GreetingController {

    private final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping
    public String greeting(Authentication authentication) {
        log.info("Logged user name - {}", authentication.getName());
        return greetingService.greet();
    }

    @GetMapping("/log")
    @Async
    public void logData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Logged user name - {}", authentication.getName());
    }

    @GetMapping("/log2")
    public void logData2() {
        DelegatingSecurityContextExecutorService executorService = new DelegatingSecurityContextExecutorService(Executors.newCachedThreadPool());
        Runnable task = () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("Logged user name - {}", authentication.getName());
        };
        executorService.submit(task);
    }
}
