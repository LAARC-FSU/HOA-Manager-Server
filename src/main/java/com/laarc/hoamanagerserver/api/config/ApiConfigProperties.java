package com.laarc.hoamanagerserver.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data
@ConfigurationProperties(prefix = "app.api")
public class ApiConfigProperties {

    public JwtProperties jwt;

    public SecurityProperties security = new SecurityProperties();

    public List<String> allowedOrigins = new ArrayList<>();

    @Data
    public static class SecurityProperties {
        private boolean enabled = true;
        private List<HttpRequestMatcher> permitAll;
    }

    @Data
    public static class JwtProperties {
        private String secretKey;
        private Long expirationTime = Duration.ofHours(24).toSeconds();
        private String tokenPrefix = "Bearer ";
    }


}
