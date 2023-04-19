package com.laarc.hoamanagerserver.exception;

import com.laarc.hoamanagerserver.exception.http.NotFoundException;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException() {
        super("User does not exist.");
    }
}
