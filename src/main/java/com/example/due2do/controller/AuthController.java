package com.example.due2do.controller;


import com.example.due2do.config.JwtUtil;
import com.example.due2do.model.Profile;
import com.example.due2do.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Profile profile) {
        profile.setPassword(new BCryptPasswordEncoder().encode(profile.getPassword()));
        userRepository.save(profile);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        Profile registeredProfile = userRepository.findByUsername(user.get("username"))
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (new BCryptPasswordEncoder().matches(user.get("password"), registeredProfile.getPassword())) {
            return ResponseEntity.ok(jwtUtil.generateToken(registeredProfile));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
