package com.laarc.hoamanagerserver.api.module.membership.controller;

import com.laarc.hoamanagerserver.api.dto.membership.MembershipResponse;
import com.laarc.hoamanagerserver.api.dto.membership.PostMembership;
import com.laarc.hoamanagerserver.api.module.membership.service.MembershipService;
import com.laarc.hoamanagerserver.shared.model.Membership;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/membership")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembershipResponse createMembership(@RequestBody @Valid PostMembership postMembership) {
        Membership membership = membershipService.createMembership(postMembership);
        return membershipService.mapToResponse(membership);
    }

}
