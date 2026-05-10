package com.samuel.frameblog.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class InstanceController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/instance")
    public Map<String, String> getInstance() {
        return Map.of(
                "service", "frameblog",
                "port", serverPort,
                "message", "Resposta da instância rodando na porta " + serverPort
        );
    }
}