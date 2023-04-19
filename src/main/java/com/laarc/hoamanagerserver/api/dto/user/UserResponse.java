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
public class UserResponse {
    private long userId;
    private String email;
    private UserRoleName userRoleName;
}
