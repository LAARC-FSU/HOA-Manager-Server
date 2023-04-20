package com.laarc.hoamanagerserver.api.dto.user;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMemberUser {

    private String membershipId;
    @Email
    private String email;
    private String password;

}
