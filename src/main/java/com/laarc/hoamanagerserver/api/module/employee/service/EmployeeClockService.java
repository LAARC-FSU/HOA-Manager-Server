package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.EmployeeClockActivityDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.EmployeeClockActivityRepository;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClockService {

    private final ModelMapper mapper;
    private final EmployeeClockActivityRepository ecaRepository;
    private final UserService userService;

    public EmployeeClockActivity postActivity(EmployeeClockActivityDTO activityDTO) {
        EmployeeClockActivity activity = mapper.map(activityDTO, EmployeeClockActivity.class);
        activity.setEmployeeUser(userService.getById(activityDTO.getEmployeeId()));
        return ecaRepository.save(activity);
    }

    public EmployeeClockActivityDTO mapToDTO(EmployeeClockActivity activity) {

        return EmployeeClockActivityDTO.builder()
                .activityId(activity.getActivityId())
                .employeeId(activity.getEmployeeUser().getUserId())
                .time(activity.getTime())
                .created(activity.getCreated())
                .activityType(activity.getActivityType())
                .build();

    }

    public List<EmployeeClockActivity> getEmployeeTimesheet(Long employeeId, LocalDate date) {
        return ecaRepository.findEmployeeClockActivitiesByEmployeeUserUserIdAndTimeAfterAndTimeBefore(
                employeeId,
                date.atStartOfDay(),
                date.atStartOfDay().plusDays(1)
        );
    }

}
