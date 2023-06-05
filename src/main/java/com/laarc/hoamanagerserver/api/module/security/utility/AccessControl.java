package com.laarc.hoamanagerserver.api.module.security.utility;

public class AccessControl {

    public static final String ADMINISTRATION = "hasAnyAuthority('EMPLOYEE', 'ADMINISTRATOR')";
    public static final String RESTRICTED = "hasAuthority('ADMINISTRATOR')";
    public static final String AUTHENTICATED = "hasAnyAuthority('EMPLOYEE', 'ADMINISTRATOR', 'MEMBER')";
    public static final String PUBLIC = "permitAll()";

}
