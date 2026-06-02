package com.solar.services;

import com.solar.dto.AuthRequest;
import com.solar.dto.AuthResponse;
import com.solar.entity.User;
import com.solar.repository.UserRepository;
import com.solar.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // Register User
    public String register(User user){

        // Encrypt Password
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // Login User
    public AuthResponse login(AuthRequest request){

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

        // Check Password
        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if(!passwordMatches){
            throw new RuntimeException("Invalid Email or Password");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}
