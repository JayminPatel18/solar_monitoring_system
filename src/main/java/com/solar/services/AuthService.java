package com.solar.services;

import com.solar.dto.AuthRequest;
import com.solar.dto.AuthResponse;
import com.solar.entity.Role;
import com.solar.entity.User;
import com.solar.exception.InvalidCredentialsException;
import com.solar.exception.ResourceNotFoundException;
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

        user.setRole(Role.USER);
        userRepository.save(user);

        return "User Registered Successfully";
    }

    // Login User
    public AuthResponse login(AuthRequest request){

        System.out.println("STEP 1");

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Email or Password"));

        System.out.println("STEP 2");

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        System.out.println("Password Match = " + passwordMatches);

        if(!passwordMatches){
            throw new InvalidCredentialsException("Invalid Email or Password");
        }

        System.out.println("STEP 3");

        String token = jwtUtil.generateToken(user.getEmail());

        System.out.println("STEP 4");

        return new AuthResponse(
                token,
                user.getRole().name(),
                user.getName(),
                user.getId()
        );
    }

    // Update User Role
    public User updateRole(Long userId, Role role){
        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id : "+userId));
        user.setRole(role);

        return userRepository.save(user);
    }
}
