package com.krishna.security2.service;

import com.krishna.security2.dto.LoginRequest;
import com.krishna.security2.model.User;
import com.krishna.security2.repo.UserRepo;
import com.krishna.security2.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager,JwtService jwtService) {
        this.userRepo = userRepo;
        this.passwordEncoder=passwordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtService=jwtService;
    }

    public User register(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(loginRequest.getUsername());
        }

        return "Login Failed";
    }
}
