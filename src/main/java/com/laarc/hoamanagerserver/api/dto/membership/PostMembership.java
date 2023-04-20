package com.laarc.hoamanagerserver.api.dto.membership;

import com.laarc.hoamanagerserver.api.dto.property.PostProperty;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostMembership {

    @Nullable
    private String membershipId;
    private List<PostMember> members;
    private List<PostProperty> properties;

}
