package com.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserAuthenticationService {

    private final Map<String, String> users = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAuthenticationService() {
        // Mock user data (passwords are hashed for security)
        users.put("user1@example.com", passwordEncoder.encode("password1"));
        users.put("user2@example.com", passwordEncoder.encode("password2"));
    }

    public boolean registerUser(String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User already exists");
        }
        users.put(email, passwordEncoder.encode(password));
        return true;
    }

    public boolean loginUser(String email, String password) {
        String hashedPassword = users.get(email);
        if (hashedPassword == null || !passwordEncoder.matches(password, hashedPassword)) {
            throw new RuntimeException("Invalid email or password");
        }
        return true;
    }
}
