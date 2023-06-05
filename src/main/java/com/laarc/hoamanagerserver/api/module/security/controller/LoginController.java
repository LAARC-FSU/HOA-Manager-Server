package com.laarc.hoamanagerserver.api.module.security.controller;

import com.laarc.hoamanagerserver.api.dto.JwtResponse;
import com.laarc.hoamanagerserver.api.dto.LoginRequest;
import com.laarc.hoamanagerserver.api.module.security.service.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthorizationService authService;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        return authService.getJwtByLoginRequest(loginRequest);
    }

}
