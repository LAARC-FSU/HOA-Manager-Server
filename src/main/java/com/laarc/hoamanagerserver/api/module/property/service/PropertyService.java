package com.laarc.hoamanagerserver.api.module.property.service;

import com.laarc.hoamanagerserver.api.dto.property.PostProperty;
import com.laarc.hoamanagerserver.api.module.membership.repository.AddressRepository;
import com.laarc.hoamanagerserver.api.module.property.repository.DeedDocumentRepository;
import com.laarc.hoamanagerserver.api.module.property.repository.PropertyRepository;
import com.laarc.hoamanagerserver.shared.model.Address;
import com.laarc.hoamanagerserver.shared.model.DeedDocument;
import com.laarc.hoamanagerserver.shared.model.Property;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final ModelMapper mapper;
    private final PropertyRepository propertyRepository;
    private final AddressRepository addressRepository;
    private final DeedDocumentRepository deedDocumentRepository;

    @Transactional
    public Property createProperty(PostProperty postProperty) {
        Property property = mapper.map(postProperty, Property.class);
        return property;
    }

    public Property saveProperty(Property property){
        return propertyRepository.save(property);
    }

}
