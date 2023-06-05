package com.laarc.hoamanagerserver.api.config;

import lombok.Data;

@Data
public class HttpRequestMatcher {
    private String method;
    private String pattern;
}
