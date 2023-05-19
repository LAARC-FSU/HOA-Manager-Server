package com.laarc.hoamanagerserver.exception.http;

import org.springframework.http.HttpStatus;

public class BadRequestException extends HttpErrorException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }

}
