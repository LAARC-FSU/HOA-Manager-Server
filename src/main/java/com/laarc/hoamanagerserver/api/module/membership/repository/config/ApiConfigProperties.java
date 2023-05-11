package com.laarc.hoamanagerserver.api.module.membership.repository.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Data
@ConfigurationProperties(prefix = "app.api")
public class ApiConfigProperties {

    public SecurityProperties security = new SecurityProperties();

    @Data
    public static class SecurityProperties {
        private boolean enabled = true;
        private List<HttpRequestMatcher> permitAll;
    }



}
