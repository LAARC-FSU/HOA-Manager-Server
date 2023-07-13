package com.laarc.hoamanagerserver.api.dto.employee;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.laarc.hoamanagerserver.api.module.utility.serialization.LocalDateTimeSerializer;
import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivityType;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeClockActivityDTO {

    @Nullable
    private Long activityId;
    private Long employeeId;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime time;
    private EmployeeClockActivityType activityType;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime created;

}
