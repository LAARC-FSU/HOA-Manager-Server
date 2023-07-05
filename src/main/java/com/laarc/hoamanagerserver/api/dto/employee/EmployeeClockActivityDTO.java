package com.laarc.hoamanagerserver.api.dto.employee;

import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivityType;
import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmployeeClockActivityDTO {

    @Nullable
    private Long activityId;
    private Long employeeId;
    private LocalDateTime time;
    private EmployeeClockActivityType activityType;
    private LocalDateTime created;

}
