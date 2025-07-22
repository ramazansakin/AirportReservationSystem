package com.sakinr.airportreservationsystem.service.impl;


import com.sakinr.airportreservationsystem.exception.CustomException;
import com.sakinr.airportreservationsystem.model.Role;
import com.sakinr.airportreservationsystem.model.User;
import com.sakinr.airportreservationsystem.repository.UserRepository;
import com.sakinr.airportreservationsystem.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    @PostConstruct
    private void postConstruct() {
        // Sample test admin user insert
        User admin = new User();
        admin.setUsername("admin-rmzn");
        admin.setPassword("pass12345");
        admin.setEmail("admin@email.com");
        admin.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        userRepository.save(admin);
    }

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    public User search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return user;
    }

    public User whoami(HttpServletRequest req) {
        return userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}
