package com.laarc.hoamanagerserver.api.module.membership.controller;

import com.laarc.hoamanagerserver.api.dto.user.RegisterMemberUser;
import com.laarc.hoamanagerserver.api.dto.user.UserResponse;
import com.laarc.hoamanagerserver.api.module.membership.service.MemberService;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MembersController {

    private final MemberService memberService;
    private final UserService userService;

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse registerMemberUser(@Valid @RequestBody RegisterMemberUser registerMemberUser) {
        return userService.mapToResponse(memberService.registerMemberUser(registerMemberUser));
    }

}
