package com.solar.controller;

import com.solar.dto.ApiResponse;
import com.solar.dto.RoleUpdateRequest;
import com.solar.dto.UserUpdateDTO;
import com.solar.entity.User;
import com.solar.repository.UserRepository;
import com.solar.services.AuthService;
import com.solar.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User APIs", description = "Operation related to users")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private AuthService auth;

    @Autowired
    private UserService userService;

    @Operation(summary = "Create new user")
    @PostMapping
    public User createUser(@Valid @RequestBody User user) {
        return repo.save(user);
    }

    @GetMapping
    @Operation(summary = "Get All Users")
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    @PutMapping("/{id}/role")
    @Operation(summary = "Update User Role")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<User> updateRole(@PathVariable Long id, @RequestBody RoleUpdateRequest request){
        User updatedUser = auth.updateRole(id, request.getRole());

        return new ApiResponse<>(
                true,
                "Role Updated Successfully",
                updatedUser
        );
    }

    @Operation(summary = "Get user by ID")
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<User>> getUserById(
            @PathVariable Long id) {

        User user = userService.getUserById(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User found",
                        user
                )
        );
    }

    @Operation(summary = "Update User")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<User>> updateUser(
            @PathVariable Long id, @Valid @RequestBody UserUpdateDTO request
    ){

        User updatedUser = userService.updateUser(id, request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User Updated Successfully",
                        updatedUser
                )
        );
    }

    @Operation(summary = "Delete User")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<User>> deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User Deleted Successfully",
                        null
                )
        );
    }
}