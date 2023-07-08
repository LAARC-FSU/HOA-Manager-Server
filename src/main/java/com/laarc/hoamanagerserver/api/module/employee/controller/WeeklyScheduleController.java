package com.laarc.hoamanagerserver.api.module.employee.controller;

import com.laarc.hoamanagerserver.api.dto.employee.*;
import com.laarc.hoamanagerserver.api.module.employee.service.*;
import com.laarc.hoamanagerserver.api.module.security.utility.AccessControl;
import com.laarc.hoamanagerserver.shared.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class WeeklyScheduleController {
    private final WeeklyScheduleService weeklyScheduleService;
    private final ShiftTimeService shiftTimeService;
    private final ShiftService shiftService;
    private final TimeFrameService timeFrameService;
    private final EmployeeScheduleService employeeScheduleService;

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @PostMapping("/make-schedule")
    public WeeklySchedule saveWeeklySchedule(@RequestBody WeeklyScheduleDTO weeklyScheduleDTO) {
        List<String> timeFrame = weeklyScheduleDTO.getTimeFrame();
        List<EmployeeScheduleDTO> employeeScheduleDTO = weeklyScheduleDTO.getSchedules();

        ShiftDTO shiftDTO = weeklyScheduleDTO.getShift();
        Shift shift = new Shift();
        shift = shiftService.saveShift(shift);
        ShiftTimeDTO shiftTimeDTO = shiftDTO.getFirstShiftTime();
        ShiftTime shiftTime = shiftTimeService.saveShiftTime(shiftTimeDTO);
        shift.setFirstShift(shiftTime.getTimeId());
        shiftTimeDTO = shiftDTO.getSecondShiftTime();
        shiftTime = shiftTimeService.saveShiftTime(shiftTimeDTO);
        shift.setSecondShift(shiftTime.getTimeId());
        shiftTimeDTO = shiftDTO.getThirdShiftTime();
        shiftTime = shiftTimeService.saveShiftTime(shiftTimeDTO);
        shift.setThirdShift(shiftTime.getTimeId());

        WeeklySchedule weeklySchedule = weeklyScheduleService.saveWeeklySchedule(weeklyScheduleDTO);
        weeklySchedule.setShiftId(shift);
        for (String time : timeFrame) {
            TimeFrame timeFrame1 = new TimeFrame();
            timeFrame1.setId(weeklySchedule.getWeeklyScheduleId());
            timeFrame1.setTime(time);
            timeFrameService.saveTime(timeFrame1);
        }
        for (EmployeeScheduleDTO employeeScheduleDTO1 : employeeScheduleDTO) {
            EmployeeSchedule employeeSchedule = employeeScheduleService.saveEmployeeSchedule(employeeScheduleDTO1);
            employeeSchedule.setScheduleId(weeklySchedule.getWeeklyScheduleId());
        }

        return weeklySchedule;
    }

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @GetMapping("/get-schedule")
    public DashboardReqestAllDTO GetWeeklyScheduleTimeFrames() {
        List<WeeklySchedule> weeklySchedules = weeklyScheduleService.getWeeklySchedule();
        List<DashboardRequestDTO> dashboardRequestDTOList = new ArrayList<>();
        for (WeeklySchedule weeklySchedule : weeklySchedules) {
            DashboardRequestDTO dashboardRequestDTO = new DashboardRequestDTO();
            dashboardRequestDTO.setPosted(weeklySchedule.isPosted());
            dashboardRequestDTO.setTimeFrameStr(weeklySchedule.getTimeFrame());
            dashboardRequestDTOList.add(dashboardRequestDTO);
        }
        DashboardReqestAllDTO dashboardReqestAllDTO = new DashboardReqestAllDTO();
        dashboardReqestAllDTO.setScheduleTimeFrames(dashboardRequestDTOList);
        return dashboardReqestAllDTO;
    }
}




