package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.EmployeeClockActivityDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.EmployeeClockActivityRepository;
import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeClockService {

    private final ModelMapper mapper;
    private final EmployeeClockActivityRepository ecaRepository;

    public EmployeeClockActivity postActivity(EmployeeClockActivityDTO activityDTO) {
        EmployeeClockActivity activity = mapper.map(activityDTO, EmployeeClockActivity.class);
        return ecaRepository.save(activity);
    }

    public EmployeeClockActivityDTO mapToDTO(EmployeeClockActivity activity) {

        return EmployeeClockActivityDTO.builder()
                .activityId(activity.getActivityId())
                .employeeId(activity.getEmployeeUserId().getUserId())
                .time(activity.getTime())
                .created(activity.getCreated())
                .activityType(activity.getActivityType())
                .build();

    }

}
