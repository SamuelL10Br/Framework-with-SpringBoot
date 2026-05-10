package com.samuel.apigateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FallbackController {

    @GetMapping("/fallback/frameblog")
    public Map<String, String> frameblogFallback() {
        return Map.of(
                "status", "indisponivel",
                "message", "O serviço Frameblog está temporariamente indisponível. Tente novamente em instantes."
        );
    }
}