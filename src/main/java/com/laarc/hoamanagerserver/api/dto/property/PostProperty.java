package com.laarc.hoamanagerserver.api.dto.property;

import com.laarc.hoamanagerserver.api.dto.address.PostAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostProperty {

    private PostAddress propertyAddress;
    private Integer deedDocumentId;
    private String deedFileName;

}
