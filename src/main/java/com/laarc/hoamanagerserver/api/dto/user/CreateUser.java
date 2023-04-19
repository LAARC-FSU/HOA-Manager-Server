package com.laarc.hoamanagerserver.api.dto.user;

import com.laarc.hoamanagerserver.shared.model.UserRoleName;
import lombok.Data;

@Data
public class CreateUser {

    private String email;
    private String password;
    private UserRoleName userRoleName;

}
