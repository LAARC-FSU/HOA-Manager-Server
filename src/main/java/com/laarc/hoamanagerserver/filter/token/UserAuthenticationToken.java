package com.laarc.hoamanagerserver.filter.token;

import com.laarc.hoamanagerserver.shared.model.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;

public class UserAuthenticationToken extends AbstractAuthenticationToken {

    private final User user;

    public UserAuthenticationToken(User user, GrantedAuthority role) {
        super(Collections.singleton(role));
        this.user = user;
        setDetails(user);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user.getEmail();
    }
}
