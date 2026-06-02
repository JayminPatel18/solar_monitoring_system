package com.solar.controller;

import com.solar.dto.AuthRequest;
import com.solar.dto.AuthResponse;
import com.solar.entity.User;
import com.solar.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Register API
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        return ResponseEntity.ok(authService.register(user));
    }

    // Login Api
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

}
