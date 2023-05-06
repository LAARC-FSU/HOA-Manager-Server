package com.laarc.hoamanagerserver.api.dto.membership;

import com.laarc.hoamanagerserver.api.dto.address.AddressDTO;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    @Nullable
    private Long memberId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String middleName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String suffix;
    @NotNull
    private AddressDTO mailingAddress;

    private boolean primaryMember;

}
