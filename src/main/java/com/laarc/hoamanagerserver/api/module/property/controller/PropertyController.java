package com.laarc.hoamanagerserver.api.module.property.controller;

import com.laarc.hoamanagerserver.api.dto.address.AddressDTO;
import com.laarc.hoamanagerserver.api.dto.property.PostProperty;
import com.laarc.hoamanagerserver.api.module.membership.service.AddressService;
import com.laarc.hoamanagerserver.api.module.property.service.PropertyService;
import com.laarc.hoamanagerserver.shared.model.Address;
import com.laarc.hoamanagerserver.shared.model.Property;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("property")
public class PropertyController {
    private final PropertyService propertyService;
    private final AddressService addressService;

    public PropertyController(PropertyService propertyService, AddressService addressService) {
        this.propertyService = propertyService;
        this.addressService = addressService;
    }

    @PostMapping("/add-property")
    public Property saveProperty(@RequestBody PostProperty postProperty){
        AddressDTO addressDTO = postProperty.getPropertyAddress();

        Property property = propertyService.createProperty(postProperty);
        Address address = addressService.saveAddress(addressDTO);
        property.setAddress(address);
        propertyService.saveProperty(property);

        return property;
    }
}
