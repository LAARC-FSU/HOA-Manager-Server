package com.laarc.hoamanagerserver.api.dto.employee;

import java.util.List;

public class WeeklyScheduleDTO {
    private long weeklyScheduleId;
    private String timeFrameStr;
    private TimeFrameDTO timeFrame;
    private ShiftDTO shift;
    private List<EmployeeScheduleDTO> employeeSchedule;
    private boolean posted;
}
