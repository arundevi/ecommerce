package com.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Map<String, UserDetails> users = new HashMap<>();

    public UserDetailsServiceImpl() {
        // Mock user data (passwords are hashed for security)
        users.put("user1", org.springframework.security.core.userdetails.User.withUsername("user1")
                .password("{bcrypt}$2a$10$ETvLQOEt8cRzCGGpgpsw6OBvQG9IZSTuukxiO3BzGAXnm6mv1M7PK")
                .roles("USER").build());

        users.put("admin", org.springframework.security.core.userdetails.User.withUsername("admin")
                .password("{bcrypt}$2a$10$ETvLQOEt8cRzCGGpgpsw6OBvQG9IZSTuukxiO3BzGAXnm6mv1M7PK")
                .roles("ADMIN").build());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }
}

