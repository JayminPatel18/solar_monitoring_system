package com.solar.controller;

import com.solar.dto.AuthRequest;
import com.solar.dto.AuthResponse;
import com.solar.entity.User;
import com.solar.services.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Authentication APIs",
        description = "User authentication and authorization"
)
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

        System.out.println("LOGIN API HIT");
        System.out.println("EMAIL = " + request.getEmail());

        return ResponseEntity.ok(
                authService.login(request)
        );
    }

}
