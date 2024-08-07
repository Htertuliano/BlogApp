package org.example.rest.api.service.Impl;

import org.example.rest.api.entity.Role;
import org.example.rest.api.entity.User;
import org.example.rest.api.payload.LoginDTO;
import org.example.rest.api.payload.RegisterDTO;
import org.example.rest.api.repository.RoleRepository;
import org.example.rest.api.repository.UserRepository;
import org.example.rest.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public String login(LoginDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "User logged in successfully";
    }

    @Override
    public String register(RegisterDTO registerDto) {
        // check for username already exist in database or not
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new RuntimeException("Username already exist");
        }

        // check for email already exist in database or not
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new RuntimeException("email already exist");
        }

        // create a new user
        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));


        // create and assign a role to the user
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_ADMIN").get();
        roles.add(userRole);
        user.setRoles(roles);

        // save the user to the database
        userRepository.save(user);
        return "User registered successfully";
    }
}
