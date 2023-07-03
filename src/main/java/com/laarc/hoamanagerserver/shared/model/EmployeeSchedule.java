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
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User employeeId;
    @ManyToOne
    @JoinColumn(name = "weekly_schedule_id")
    private WeeklySchedule scheduleId;

    private String sun;
    private String mon;
    private String tue;
    private String wed;
    private String thr;
    private String sat;
    private boolean vac;
}
