package com.laarc.hoamanagerserver.exception.handler;

import com.laarc.hoamanagerserver.exception.http.HttpErrorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class HttpErrorExceptionHandler {

    @ExceptionHandler(HttpErrorException.class)
    public ResponseEntity<String> handleHttpErrorException(HttpErrorException e) {
        String message = e.getMessage();
        log.error("HTTP Error: {} Message: {}", e.getStatus(), e.getMessage());
        return ResponseEntity.status(e.getStatus()).body(message);
    }

}
