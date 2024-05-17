package com.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.utils.JwtUtil;

@Configuration
public class SecurityConfig extends AbstractHttpConfigurer<SecurityConfig, HttpSecurity> {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public SecurityConfig(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http
    	.authorizeRequests()
        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products/**").hasRole("USER")
                .requestMatchers(HttpMethod.POST, "/api/products/**").hasRole("ADMIN")
                .anyRequest().authenticated();

        http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager, jwtUtil), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }
}
