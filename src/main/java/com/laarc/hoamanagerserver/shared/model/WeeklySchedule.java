package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class WeeklySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long weeklyScheduleId;

    private String timeFrame;

    private Long timeFrameId;

    private Long shiftId;
}
