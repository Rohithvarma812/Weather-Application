package com.weatherapp.controller;

import com.weatherapp.dto.AuthRequest;
import com.weatherapp.dto.AuthResponse;
import com.weatherapp.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<AuthResponse>> login(@RequestBody @Valid AuthRequest request) {
        return Mono.just(ResponseEntity.ok(new AuthResponse(jwtService.issueToken(request.username()))));
    }
}
