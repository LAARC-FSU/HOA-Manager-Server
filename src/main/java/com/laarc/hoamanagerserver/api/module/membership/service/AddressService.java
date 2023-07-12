package com.laarc.hoamanagerserver.api.module.membership.service;

import com.laarc.hoamanagerserver.api.dto.address.AddressDTO;
import com.laarc.hoamanagerserver.api.module.membership.repository.AddressRepository;
import com.laarc.hoamanagerserver.shared.model.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    public AddressService(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }
    public Address saveAddress(AddressDTO addressDTO){
        return addressRepository.save(modelMapper.map(addressDTO, Address.class));
    }
}
