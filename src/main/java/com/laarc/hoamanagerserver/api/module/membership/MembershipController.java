package com.laarc.hoamanagerserver.api.module.membership;

import com.laarc.hoamanagerserver.api.dto.membership.MembershipResponse;
import com.laarc.hoamanagerserver.api.dto.membership.PostMembership;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MembershipResponse createMember(@RequestBody @Valid PostMembership postMembership) {
        return null;
    }

}
