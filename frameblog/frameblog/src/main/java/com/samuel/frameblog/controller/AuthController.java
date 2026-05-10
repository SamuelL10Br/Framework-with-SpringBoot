package com.samuel.frameblog.controller;

import com.samuel.frameblog.dto.LoginRequest;
import com.samuel.frameblog.dto.LoginResponse;
import com.samuel.frameblog.exception.ResourceNotFoundException;
import com.samuel.frameblog.model.User;
import com.samuel.frameblog.repository.UserRepository;
import com.samuel.frameblog.service.TokenService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    public AuthController(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(request.getEmail())
                        && u.getPassword().equals(request.getPassword()))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("E-mail ou senha inválidos"));

        String token = tokenService.generateToken(user);

        return new LoginResponse(token);
    }
}