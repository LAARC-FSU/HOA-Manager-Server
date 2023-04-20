package com.laarc.hoamanagerserver.api.dto.user;

import com.laarc.hoamanagerserver.shared.model.UserRoleName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostUser {

    private String email;
    private String password;
    private UserRoleName userRoleName;

}
