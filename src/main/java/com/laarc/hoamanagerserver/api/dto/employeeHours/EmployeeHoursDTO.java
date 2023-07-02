package com.laarc.hoamanagerserver.api.dto.employeeHours;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeHoursDTO {

    @Nullable
    private Long DaysID;
    @Nullable
    private String UserId;
    @NotBlank
    private String timeIn;
    private String timeOut;
    @Nullable
    private String lunchStart;
    @Nullable
    private String lunchEnd;

}
