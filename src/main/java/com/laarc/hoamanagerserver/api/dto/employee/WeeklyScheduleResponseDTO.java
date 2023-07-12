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
public class WeeklyScheduleResponseDTO {
    private String timeFrameStr;
    private ArrayList<String> timeFrame;

    private ShiftTimeResponseDTO firstShiftTime;

    private ShiftTimeResponseDTO secondShiftTime;
    private ShiftTimeResponseDTO thirdShiftTime;
    private List<EmployeeScheduleDTO> schedules;
}
