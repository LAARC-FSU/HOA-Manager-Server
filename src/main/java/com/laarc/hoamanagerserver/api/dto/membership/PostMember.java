package com.laarc.hoamanagerserver.api.dto.membership;

import com.laarc.hoamanagerserver.api.dto.address.PostAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMember {

    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private PostAddress mailingAddress;

}
