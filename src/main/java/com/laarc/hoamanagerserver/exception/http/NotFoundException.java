package com.laarc.hoamanagerserver.exception.http;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HttpErrorException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
