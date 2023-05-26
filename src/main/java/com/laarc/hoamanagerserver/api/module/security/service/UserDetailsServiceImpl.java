package com.laarc.hoamanagerserver.api.module.security.service;

import com.laarc.hoamanagerserver.api.module.user.repository.UserRepository;
import com.laarc.hoamanagerserver.exception.http.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UnauthorizedException("User with email '" + email + "' does not exist.")
        );
    }
}
