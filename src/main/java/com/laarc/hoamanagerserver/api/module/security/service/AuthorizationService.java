package com.laarc.hoamanagerserver.api.module.security.service;

import com.laarc.hoamanagerserver.api.dto.JwtResponse;
import com.laarc.hoamanagerserver.api.dto.LoginRequest;
import com.laarc.hoamanagerserver.api.module.security.utility.JwtUtil;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
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
    private final UserService userService;

    public JwtResponse getJwtByLoginRequest(LoginRequest loginRequest) {

        authenticateLogin(loginRequest);

        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtUtil.generateToken(user);
        return JwtResponse.builder()
                .token(token)
                .build();
    }

    public void authenticateLogin(LoginRequest loginRequest) {
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String password = user.getPassword();

        // Check password
        boolean correctPassword = encoder.matches(loginRequest.getPassword(), password);

        if (!correctPassword)
            throw new UnauthorizedException("Password is incorrect.");
    }

}
