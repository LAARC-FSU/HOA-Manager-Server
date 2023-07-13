package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.EmployeeScheduleDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.EmployeeScheduleRepository;
import com.laarc.hoamanagerserver.shared.model.EmployeeSchedule;
import com.laarc.hoamanagerserver.shared.model.TimeFrame;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeScheduleService {
    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final ModelMapper modelMapper;

    public EmployeeScheduleService(EmployeeScheduleRepository employeeScheduleRepository, ModelMapper modelMapper) {
        this.employeeScheduleRepository = employeeScheduleRepository;
        this.modelMapper = modelMapper;
    }

    public EmployeeSchedule saveEmployeeSchedule(EmployeeScheduleDTO employeeScheduleDTO){
        EmployeeSchedule employeeSchedule = modelMapper.map(employeeScheduleDTO, EmployeeSchedule.class);
        return employeeScheduleRepository.save(employeeSchedule);
    }

    public List<EmployeeSchedule> getEmpSchedule(Long scheduleId){return employeeScheduleRepository.findByScheduleId(scheduleId);}
}
