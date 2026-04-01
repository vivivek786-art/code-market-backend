package com.baalvionService.JobPortalApplication.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baalvionService.JobPortalApplication.entities.User;
import com.baalvionService.JobPortalApplication.enums.UserRole;
import com.baalvionService.JobPortalApplication.model.SignupRequest;
import com.baalvionService.JobPortalApplication.repositories.UserRepository;
import com.baalvionService.JobPortalApplication.securities.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // ✅ FIX

    // ================= SIGNUP =================
    @PostMapping("/signup")
    public Map<String, String> signup(@Valid @RequestBody SignupRequest req) {

        if (userRepository.existsByEmail(req.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());

        // 🔐 Encrypt password
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        // ✅ Safe role handling
        try {
            user.setRole(UserRole.valueOf(req.getRole().toUpperCase()));
        } catch (Exception e) {
            throw new RuntimeException("Invalid role. Use STUDENT or COMPANY");
        }

        user.setSkills(req.getSkills());
        user.setExperience(req.getExperience());

        userRepository.save(user);

        return Map.of("message", "User registered successfully");
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {

        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.get("email"),
                        req.get("password")
                )
        );

        // ✅ Use authenticated principal (IMPORTANT)
        String email = auth.getName();

        String token = jwtUtil.generateToken(email);

        return Map.of("token", token);
    }
}