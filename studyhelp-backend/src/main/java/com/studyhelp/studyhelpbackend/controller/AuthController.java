package com.studyhelp.studyhelpbackend.controller;

import com.studyhelp.studyhelpbackend.dto.SignupRequest;
import com.studyhelp.studyhelpbackend.model.User;
import com.studyhelp.studyhelpbackend.repository.UserRepository;
import com.studyhelp.studyhelpbackend.dto.LoginRequest;
import com.studyhelp.studyhelpbackend.security.JwtUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getEmail(), encodedPassword);
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        // Check if user exists
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        // Match password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }

        // Generate JWT
        String token = jwtUtil.generateToken(user.getEmail());

        return ResponseEntity.ok(token);
    }

}
