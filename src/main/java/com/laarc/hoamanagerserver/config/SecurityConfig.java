package com.laarc.hoamanagerserver.config;

import com.laarc.hoamanagerserver.api.module.membership.repository.config.ApiConfigProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j(topic = "Security Configuration")
public class SecurityConfig {

    private final ApiConfigProperties apiConfigProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecretKey secretKey() {
        return new SecretKeySpec(apiConfigProperties.jwt.getSecretKey().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        if (apiConfigProperties.getSecurity().isEnabled()) {
            authorizeRequests(http);
        } else {
            allowAllRequests(http);
        }
        disableDefaultLoginForm(http);

        http.csrf().disable();

        return http.build();

    }

    private void authorizeRequests(HttpSecurity http) throws Exception {

        List<AntPathRequestMatcher> matchers = apiConfigProperties
                .getSecurity()
                .getPermitAll()
                .stream()
                .map(matcher -> StringUtils.hasText(matcher.getMethod()) ?
                        new AntPathRequestMatcher(matcher.getPattern(), matcher.getMethod()) :
                        new AntPathRequestMatcher(matcher.getPattern())).toList();

        http
                .authorizeHttpRequests()
                .requestMatchers(matchers.toArray(new RequestMatcher[0])).permitAll()
                .anyRequest().authenticated();
    }

    private void allowAllRequests(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest().permitAll();
    }

    private void disableDefaultLoginForm(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }

}
