package com.laarc.hoamanagerserver.api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "\\d{8}-\\d{2}", message = "Membership ID format is invalid.")
    private String membershipId;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
