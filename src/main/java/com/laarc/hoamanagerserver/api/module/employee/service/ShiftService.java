package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.ShiftDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.ShiftRepository;
import com.laarc.hoamanagerserver.shared.model.Shift;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShiftService {

    private final ShiftRepository shiftRepository;

    public ShiftService(ShiftRepository shiftRepository, ModelMapper modelMapper) {
        this.shiftRepository = shiftRepository;

    }

    public Shift saveShift(Shift shift) {
        return shiftRepository.save(shift);
    }
}
