package com.laarc.hoamanagerserver.api.module.security.service;

import com.laarc.hoamanagerserver.api.dto.JwtResponse;
import com.laarc.hoamanagerserver.api.dto.LoginRequest;
import com.laarc.hoamanagerserver.api.module.security.utility.JwtUtil;
import com.laarc.hoamanagerserver.exception.http.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtResponse getJwtByLoginRequest(LoginRequest loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String username = user.getUsername();
        String password = user.getPassword();

        // Check password
        boolean correctPassword = encoder.matches(loginRequest.getPassword(), password);

        if (!correctPassword)
            throw new UnauthorizedException("Password is incorrect.");

        String token = jwtUtil.generateToken(username);
        return JwtResponse.builder()
                .token(token)
                .build();
    }

}
