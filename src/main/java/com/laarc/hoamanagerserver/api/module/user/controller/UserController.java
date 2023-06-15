package com.laarc.hoamanagerserver.api.module.user.controller;

import com.laarc.hoamanagerserver.api.dto.user.PostUser;
import com.laarc.hoamanagerserver.api.dto.user.UserResponse;
import com.laarc.hoamanagerserver.api.module.security.utility.AccessControl;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import com.laarc.hoamanagerserver.shared.model.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponse createUser(@RequestBody @Valid PostUser postUser) {
        User createdUser = userService.createUser(postUser);
        return userService.mapToResponse(createdUser);
    }

}
