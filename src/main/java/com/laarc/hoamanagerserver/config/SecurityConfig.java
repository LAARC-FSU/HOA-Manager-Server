package com.laarc.hoamanagerserver.config;

import com.laarc.hoamanagerserver.api.config.ApiConfigProperties;
import com.laarc.hoamanagerserver.api.module.security.utility.JwtUtil;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import com.laarc.hoamanagerserver.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j(topic = "Security Configuration")
@EnableMethodSecurity
public class SecurityConfig implements WebMvcConfigurer {

    private final ApiConfigProperties apiConfigProperties;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        if (apiConfigProperties.getSecurity().isEnabled()) {
            authorizeRequests(http);
        } else {
            allowAllRequests(http);
        }
        disableDefaultLoginForm(http);
        disableCsrf(http);

        return http.build();

    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtUtil, userService);
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

    private void disableCsrf(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins(apiConfigProperties.allowedOrigins.toArray(new String[0]));
    }
}
