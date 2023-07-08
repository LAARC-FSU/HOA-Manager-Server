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
    private String name;
    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thu;
    private String fri;
    private String sat;
    private boolean vac;
}
