package com.laarc.hoamanagerserver.api.dto.property;

import com.laarc.hoamanagerserver.api.dto.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyResponse {

    private Long propertyId;
    private AddressDTO address;
    private String deedDocumentFileName;

}
