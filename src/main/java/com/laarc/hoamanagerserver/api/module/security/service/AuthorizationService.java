package com.laarc.hoamanagerserver.api.module.security.service;

import com.laarc.hoamanagerserver.api.dto.JwtResponse;
import com.laarc.hoamanagerserver.api.dto.LoginRequest;
import com.laarc.hoamanagerserver.api.module.membership.service.MemberService;
import com.laarc.hoamanagerserver.api.module.security.utility.JwtUtil;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import com.laarc.hoamanagerserver.exception.http.UnauthorizedException;
import com.laarc.hoamanagerserver.shared.model.Member;
import com.laarc.hoamanagerserver.shared.model.Person;
import com.laarc.hoamanagerserver.shared.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorizationService {

    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserService userService;
    private final MemberService memberService;

    public JwtResponse getJwtByLoginRequest(LoginRequest loginRequest) {
        Optional<User> user1 = userService.getByEmail(loginRequest.getUsername());
        Optional<Member> member = memberService.getMember(user1.get().getUserId());
        Person person = member.get().getPerson();
        String name = person.getFirstName();

        authenticateLogin(loginRequest);
        UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = jwtUtil.generateToken(user);

        return JwtResponse.builder()
                .token(token)
                .name(name)
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
