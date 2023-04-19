package com.laarc.hoamanagerserver.exception.http;

import org.springframework.http.HttpStatus;

public class ConflictException extends HttpErrorException {

    public ConflictException(String message) {
        super(HttpStatus.CONFLICT,  message);
    }

}
