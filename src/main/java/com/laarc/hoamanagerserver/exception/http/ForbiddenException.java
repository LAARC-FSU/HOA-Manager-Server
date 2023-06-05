package com.laarc.hoamanagerserver.exception.http;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends HttpErrorException {

    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

}
