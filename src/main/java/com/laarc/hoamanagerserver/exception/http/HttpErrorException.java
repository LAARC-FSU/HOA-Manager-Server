package com.laarc.hoamanagerserver.exception.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpErrorException extends RuntimeException {

    private final HttpStatus status;

    public HttpErrorException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
