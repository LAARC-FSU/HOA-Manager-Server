package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.WeeklyScheduleDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.*;
import com.laarc.hoamanagerserver.shared.model.WeeklySchedule;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeScheduleService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;
    private final TimeFrameRepository timeFrameRepository;
    private final ShiftRepository shiftRepository;
    private final ShiftTimeRepository shiftTimeRepository;
    private final EmployeeScheduleRepository employeeScheduleRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public WeeklySchedule createWeeklySchedule(WeeklyScheduleDTO weeklyScheduleDTO){

        WeeklySchedule weeklySchedule = modelMapper.map(weeklyScheduleDTO, WeeklySchedule.class);


        return weeklyScheduleRepository.save(weeklySchedule);
    }

}
