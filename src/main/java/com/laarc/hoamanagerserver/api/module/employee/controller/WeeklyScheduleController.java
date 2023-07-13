package com.laarc.hoamanagerserver.api.module.employee.controller;

import com.laarc.hoamanagerserver.api.dto.employee.*;
import com.laarc.hoamanagerserver.api.module.employee.service.*;
import com.laarc.hoamanagerserver.api.module.security.utility.AccessControl;
import com.laarc.hoamanagerserver.shared.model.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class WeeklyScheduleController {
    private final WeeklyScheduleService weeklyScheduleService;
    private final ShiftTimeService shiftTimeService;
    private final ShiftService shiftService;
    private final TimeFrameService timeFrameService;
    private final EmployeeScheduleService employeeScheduleService;

    private final ModelMapper modelMapper;

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @PostMapping("/make-schedule")
    public WeeklySchedule saveWeeklySchedule(@RequestBody WeeklyScheduleDTO weeklyScheduleDTO) {
        List<String> timeFrame = weeklyScheduleDTO.getTimeFrame();
        List<EmployeeScheduleDTO> employeeScheduleDTO = weeklyScheduleDTO.getSchedules();

        ShiftDTO shiftDTO = weeklyScheduleDTO.getShift();

        Shift shift = new Shift();
        shift = shiftService.saveShift(shift);
        ShiftTime shiftTime = shiftTimeService.saveShiftTime(shiftDTO.getFirstShiftTime());
        shift.setFirstShift(shiftTime.getTimeId());
        shiftTime = shiftTimeService.saveShiftTime(shiftDTO.getSecondShiftTime());
        shift.setSecondShift(shiftTime.getTimeId());
        shiftTime = shiftTimeService.saveShiftTime(shiftDTO.getThirdShiftTime());
        shift.setThirdShift(shiftTime.getTimeId());

        WeeklySchedule weeklySchedule = weeklyScheduleService.saveWeeklySchedule(weeklyScheduleDTO);
        weeklySchedule.setShiftId(shift);
        for (String time : timeFrame) {
            TimeFrame timeFrame1 = new TimeFrame();
            timeFrame1.setWeeklyScheduleId(weeklySchedule.getWeeklyScheduleId());
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
            dashboardRequestDTO.setId(weeklySchedule.getWeeklyScheduleId());
            dashboardRequestDTO.setPosted(weeklySchedule.isPosted());
            dashboardRequestDTO.setTimeFrameStr(weeklySchedule.getTimeFrame());
            dashboardRequestDTOList.add(dashboardRequestDTO);
        }
        DashboardReqestAllDTO dashboardReqestAllDTO = new DashboardReqestAllDTO();
        dashboardReqestAllDTO.setScheduleTimeFrames(dashboardRequestDTOList);
        return dashboardReqestAllDTO;
    }

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @GetMapping("/current-schedule/{id}")
    public WeeklyScheduleResponseDTO GetCurrentSchedule(@PathVariable Long id){
        WeeklyScheduleResponseDTO weeklyScheduleResponseDTO = new WeeklyScheduleResponseDTO();
        Optional<WeeklySchedule> weeklySchedule = weeklyScheduleService.update(id);
        List<TimeFrame> timeFrames = timeFrameService.getTimeFrames(id);
        ArrayList<String> timeFrameDto = new ArrayList<>();
        for(TimeFrame time : timeFrames){
            timeFrameDto.add(time.getTime());
        }
        weeklyScheduleResponseDTO.setTimeFrame(timeFrameDto);
        weeklyScheduleResponseDTO.setTimeFrameStr(weeklySchedule.get().getTimeFrame());
        Shift shift = weeklySchedule.get().getShiftId();
        Optional<ShiftTime> firstShift = shiftTimeService.getShiftTime(shift.getFirstShift());
        Optional<ShiftTime> secondShift = shiftTimeService.getShiftTime(shift.getSecondShift());
        Optional<ShiftTime> thirdShift = shiftTimeService.getShiftTime(shift.getThirdShift());
        ShiftTimeResponseDTO shiftTimeResponseDTO1 = modelMapper.map(firstShift, ShiftTimeResponseDTO.class);
        shiftTimeResponseDTO1.setId("first shift");
        ShiftTimeResponseDTO shiftTimeResponseDTO2 = modelMapper.map(secondShift, ShiftTimeResponseDTO.class);
        shiftTimeResponseDTO2.setId("second shift");
        ShiftTimeResponseDTO shiftTimeResponseDTO3 = modelMapper.map(thirdShift, ShiftTimeResponseDTO.class);
        shiftTimeResponseDTO3.setId("third shift");
        weeklyScheduleResponseDTO.setFirstShiftTime(shiftTimeResponseDTO1);
        weeklyScheduleResponseDTO.setSecondShiftTime(shiftTimeResponseDTO2);
        weeklyScheduleResponseDTO.setThirdShiftTime(shiftTimeResponseDTO3);

        List<EmployeeSchedule>  employeeSchedules = employeeScheduleService.getEmpSchedule(id);
        List<EmployeeScheduleDTO> employeeScheduleDTOS = new ArrayList<>();
        for (EmployeeSchedule employeeSchedule : employeeSchedules) {
            EmployeeScheduleDTO employeeScheduleDTO = modelMapper.map(employeeSchedule, EmployeeScheduleDTO.class);
            employeeScheduleDTOS.add(employeeScheduleDTO);
        }
        weeklyScheduleResponseDTO.setSchedules(employeeScheduleDTOS);

        return weeklyScheduleResponseDTO;
    }

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @PutMapping("/update/{id}/{id2}")
    public void UpdatePosted(@PathVariable Long id, @PathVariable Long id2) {

        Optional<WeeklySchedule> weeklySchedule = weeklyScheduleService.update(id);
        if (weeklySchedule.isPresent()) {
            WeeklySchedule weeklySchedule1 = weeklySchedule.get();
            weeklySchedule1.setPosted(true);
            weeklyScheduleService.saveWeeklySchedule(weeklySchedule1);
        }
        if (id2 != null) {
            weeklySchedule = weeklyScheduleService.update(id2);
            if (weeklySchedule.isPresent()) {
                WeeklySchedule weeklySchedule1 = weeklySchedule.get();
                weeklySchedule1.setPosted(false);
                weeklyScheduleService.saveWeeklySchedule(weeklySchedule1);
            }
        }
    }

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @DeleteMapping("/delete/{id}")
    public void DeleteSchedule(@PathVariable Long id){
       weeklyScheduleService.delete(id);
    }
}





