package com.laarc.hoamanagerserver.api.module.employee.service;


import com.laarc.hoamanagerserver.api.dto.employee.ShiftTimeDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.ShiftTimeRepository;
import com.laarc.hoamanagerserver.shared.model.ShiftTime;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShiftTimeService {

    private final ShiftTimeRepository shiftTimeRepository;

    private final ModelMapper modelMapper;

    public ShiftTimeService(ShiftTimeRepository shiftTimeRepository, ModelMapper modelMapper) {
        this.shiftTimeRepository = shiftTimeRepository;
        this.modelMapper = modelMapper;
    }

    public ShiftTime saveShiftTime(ShiftTimeDTO shiftTimeDTO) {
        ShiftTime shiftTime = modelMapper.map(shiftTimeDTO, ShiftTime.class);
        return shiftTimeRepository.save(shiftTime);
    }
}
