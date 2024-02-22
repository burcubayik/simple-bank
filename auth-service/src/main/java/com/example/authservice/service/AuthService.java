package com.example.authservice.service;

import com.example.authservice.dto.AuthResponse;
import com.example.authservice.dto.AuthenticateUserRequest;
import com.example.authservice.dto.RegisterUserRequest;
import com.example.authservice.entity.User;
import com.example.authservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final  JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register (RegisterUserRequest userRequest){
        User user = User.builder()
                .name(userRequest.getName())
                .lastname(userRequest.getLastname())
                .nationality(userRequest.getNationality())
                .birthdate(userRequest.getBirthdate())
                .email(userRequest.getEmail())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .build();
        user = userRepository.save(user);
        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }

    public AuthResponse authenticate (AuthenticateUserRequest userRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userRequest.getEmail(),userRequest.getPassword())
        );

        User user = userRepository.findByEmail(userRequest.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }



}
