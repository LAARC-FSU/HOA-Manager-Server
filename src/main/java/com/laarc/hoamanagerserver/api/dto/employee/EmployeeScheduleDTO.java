package com.laarc.hoamanagerserver.api.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeScheduleDTO {

    private Long scheduleId;
    private String empName;
    private String empSun;
    private String empMon;
    private String empTue;
    private String empWed;
    private String empThu;
    private String empFri;
    private String empSat;
    private Boolean empVac;
}
