package com.laarc.hoamanagerserver.api.dto.employeeHours;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeHoursResponse {
    private String UserId;
    private List<EmployeeHoursDTO> hours;
}
