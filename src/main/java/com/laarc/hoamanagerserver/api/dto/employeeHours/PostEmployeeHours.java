package com.laarc.hoamanagerserver.api.dto.employeeHours;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostEmployeeHours {

    @Nullable
    private String DaysId;
    private List<EmployeeHoursDTO> hours;

}
