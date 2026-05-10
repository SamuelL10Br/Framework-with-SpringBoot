package com.samuel.frameblog.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiscoveryController {

    private final DiscoveryClient discoveryClient;

    public DiscoveryController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/discovery/services")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/discovery/frameblog")
    public List<ServiceInstance> getFrameblogInstances() {
        return discoveryClient.getInstances("FRAMEBLOG");
    }

    @GetMapping("/discovery/frameblog-lower")
    public List<ServiceInstance> getFrameblogInstancesLower() {
        return discoveryClient.getInstances("frameblog");
    }
}