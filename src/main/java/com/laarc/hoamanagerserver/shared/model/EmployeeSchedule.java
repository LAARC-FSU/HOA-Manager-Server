package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class EmployeeSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeScheduleId;

    private Long employeeId;
    private Long scheduleId;

    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thr;
    private String sat;
    private boolean vac;
}
