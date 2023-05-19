package com.laarc.hoamanagerserver.api.module.membership.repository.config;

import lombok.Data;

@Data
public class HttpRequestMatcher {
    private String method;
    private String pattern;
}
