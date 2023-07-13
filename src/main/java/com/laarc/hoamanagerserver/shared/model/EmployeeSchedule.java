package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EmployeeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeScheduleId;
    private Long scheduleId;
    private String empName;
    private String empSun;
    private String empMon;
    private String empTue;
    private String empWed;
    private String empThu;
    private String empFri;
    private String empSat;
    private boolean empVac;
}
