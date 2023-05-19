package com.laarc.hoamanagerserver.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMemberUser {

    @NotBlank
    private String membershipId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
