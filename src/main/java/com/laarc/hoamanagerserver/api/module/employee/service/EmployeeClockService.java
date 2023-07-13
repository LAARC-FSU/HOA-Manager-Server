package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.EmployeeClockActivityDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.EmployeeClockActivityRepository;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import com.laarc.hoamanagerserver.exception.http.BadRequestException;
import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivity;
import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivityType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeClockService {

    private final ModelMapper mapper;
    private final EmployeeClockActivityRepository ecaRepository;
    private final UserService userService;

    @Transactional
    public EmployeeClockActivity postActivity(EmployeeClockActivityDTO activityDTO) {
        EmployeeClockActivity activity = mapper.map(activityDTO, EmployeeClockActivity.class);
        activity.setEmployeeUser(userService.getById(activityDTO.getEmployeeId()));

        EmployeeClockActivity lastActivity = getLastActivity(activity.getEmployeeUser().getUserId());
        EmployeeClockActivityType activityType = activity.getActivityType();

        log.info("User {} is attempting to clock {} at {}.", activity.getEmployeeUser().getUsername(), activityType, activity.getTime());

        if (lastActivity == null) {
            if (activityType == EmployeeClockActivityType.LUNCH_IN ||
                    activityType == EmployeeClockActivityType.CLOCK_OUT ||
                    activityType == EmployeeClockActivityType.LUNCH_OUT) {
                throw new BadRequestException("Invalid clock activity transition");
            }
        } else {
            EmployeeClockActivityType lastActivityType = lastActivity.getActivityType();

            if (activityType == EmployeeClockActivityType.CLOCK_IN && lastActivityType != EmployeeClockActivityType.CLOCK_OUT) {
                throw new BadRequestException("Invalid clock activity transition");
            }

            if (activityType == EmployeeClockActivityType.LUNCH_IN && lastActivityType != EmployeeClockActivityType.CLOCK_IN) {
                throw new BadRequestException("Invalid clock activity transition");
            }

            if (activityType == EmployeeClockActivityType.LUNCH_OUT && lastActivityType != EmployeeClockActivityType.LUNCH_IN) {
                throw new BadRequestException("Invalid clock activity transition");
            }

            if (activityType == EmployeeClockActivityType.CLOCK_OUT && lastActivityType == EmployeeClockActivityType.CLOCK_OUT) {
                throw new BadRequestException("Invalid clock activity transition");
            } else {
                if (lastActivityType == EmployeeClockActivityType.LUNCH_IN) {

                    EmployeeClockActivity lunchOutActivity = new EmployeeClockActivity();
                    lunchOutActivity.setEmployeeUser(lastActivity.getEmployeeUser());
                    lunchOutActivity.setActivityType(EmployeeClockActivityType.LUNCH_OUT);
                    lunchOutActivity.setTime(activity.getTime());

                    ecaRepository.save(lunchOutActivity);
                }
            }
        }

        return ecaRepository.save(activity);
    }

    public EmployeeClockActivity getLastActivity(Long employeeId) {
        return ecaRepository.findTopByEmployeeUserUserIdOrderByTimeDesc(employeeId);
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
