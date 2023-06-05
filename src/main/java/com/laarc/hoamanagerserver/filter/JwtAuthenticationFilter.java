package com.laarc.hoamanagerserver.filter;

import com.laarc.hoamanagerserver.api.module.security.utility.JwtUtil;
import com.laarc.hoamanagerserver.exception.http.ForbiddenException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasText(token) && token.startsWith("Bearer ") && jwtUtil.validateToken(token)) {
            Authentication authentication = createAuthentication(token);
            log.info("Name: {} Authorities: {}", authentication.getPrincipal(), authentication.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private Authentication createAuthentication(String token) {
        String username = jwtUtil.extractUsername(token);
        GrantedAuthority role = jwtUtil.extractUserAuthority(token);

        return new UsernamePasswordAuthenticationToken(username, "", Collections.singleton(role));
    }
}
