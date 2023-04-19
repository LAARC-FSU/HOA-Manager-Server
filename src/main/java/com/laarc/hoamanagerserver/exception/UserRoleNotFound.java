package com.laarc.hoamanagerserver.exception;

import com.laarc.hoamanagerserver.exception.http.NotFoundException;

public class UserRoleNotFound extends NotFoundException {
    public UserRoleNotFound() {
        super("User role does not exist.");
    }
}
