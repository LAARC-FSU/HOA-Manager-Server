package com.laarc.hoamanagerserver.api.module.employee.service;


import com.laarc.hoamanagerserver.api.dto.employee.ShiftTimeDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.ShiftTimeRepository;
import com.laarc.hoamanagerserver.shared.model.ShiftTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShiftTimeService {

    private final ShiftTimeRepository shiftTimeRepository;
    private final ModelMapper modelMapper;

    public ShiftTimeService(ShiftTimeRepository shiftTimeRepository, ModelMapper modelMapper) {
        this.shiftTimeRepository = shiftTimeRepository;
        this.modelMapper = modelMapper;
    }

    public ShiftTime saveShiftTime(ShiftTimeDTO shiftTimeDTO) {
        return shiftTimeRepository.save(modelMapper.map(shiftTimeDTO, ShiftTime.class));
    }
    public Optional<ShiftTime> getShiftTime(Long id){
        return  shiftTimeRepository.findById(id);
    }
}
