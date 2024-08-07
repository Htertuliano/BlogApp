package org.example.rest.api.service;

import org.example.rest.api.payload.LoginDTO;
import org.example.rest.api.payload.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
    String register(RegisterDTO registerDto);
}

