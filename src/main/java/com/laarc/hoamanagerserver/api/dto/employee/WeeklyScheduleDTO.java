package com.laarc.hoamanagerserver.api.dto.employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeeklyScheduleDTO {

    private  Long weeklyScheduleId;
    private ArrayList<String> timeFrame;
    private String timeFrameStr;
    private ShiftDTO shift;
    private List<EmployeeScheduleDTO> schedules;
    private boolean posted;
}
