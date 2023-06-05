package com.laarc.hoamanagerserver.exception.http;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HttpErrorException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
