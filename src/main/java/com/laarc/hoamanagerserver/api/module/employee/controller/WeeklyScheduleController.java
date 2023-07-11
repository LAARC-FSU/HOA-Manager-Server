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
    public WeeklyScheduleDTO GetCurrentSchedule(@PathVariable Long id){
        WeeklyScheduleDTO weeklyScheduleDTO = new WeeklyScheduleDTO();
        Optional<WeeklySchedule> weeklySchedule = weeklyScheduleService.update(id);
        weeklyScheduleDTO.setWeeklyScheduleId(id);
        List<TimeFrame> timeFrames = timeFrameService.getTimeFrames(id);
        ArrayList<String> timeFrameDto = new ArrayList<>();
        for(TimeFrame time : timeFrames){
            timeFrameDto.add(time.toString());
        }
        weeklyScheduleDTO.setTimeFrame(timeFrameDto);
        weeklyScheduleDTO.setTimeFrameStr(weeklySchedule.get().getTimeFrame());
        Shift shift = weeklySchedule.get().getShiftId();
        Optional<ShiftTime> firstShift = shiftTimeService.getShiftTime(shift.getFirstShift());
        Optional<ShiftTime> secondShift = shiftTimeService.getShiftTime(shift.getSecondShift());
        Optional<ShiftTime> thirdShift = shiftTimeService.getShiftTime(shift.getThirdShift());
        List<Optional<ShiftTime>> shiftTimes = new ArrayList<>();
        shiftTimes.add(firstShift);
        shiftTimes.add(secondShift);
        shiftTimes.add(thirdShift);
        List<ShiftTimeDTO> shiftTimeDTOS = new ArrayList<>();
        for(Optional<ShiftTime> shiftTime : shiftTimes){
            ShiftTimeDTO shiftTimeDTO = new ShiftTimeDTO();
            shiftTimeDTO.setShiftTimeId(shiftTime.get().getTimeId());
            shiftTimeDTO.setStart(shiftTime.get().getStart());
            shiftTimeDTO.setEnd(shiftTime.get().getEnd());
            shiftTimeDTO.setEnabled(shiftTime.get().isEnabled());
            shiftTimeDTOS.add(shiftTimeDTO);
        }
        ShiftDTO shiftDTO = new ShiftDTO();
        shiftDTO.setShiftId(shift.getShiftId());
        shiftDTO.setFirstShiftTime(shiftTimeDTOS.get(0));
        shiftDTO.setSecondShiftTime(shiftTimeDTOS.get(1));
        shiftDTO.setThirdShiftTime(shiftTimeDTOS.get(2));
        weeklyScheduleDTO.setShift(shiftDTO);

        List<EmployeeSchedule>  employeeSchedules = employeeScheduleService.getEmpSchedule(id);
        List<EmployeeScheduleDTO> employeeScheduleDTOS = new ArrayList<>();
        for (EmployeeSchedule employeeSchedule : employeeSchedules) {
            EmployeeScheduleDTO employeeScheduleDTO = modelMapper.map(employeeSchedule, EmployeeScheduleDTO.class);
            employeeScheduleDTOS.add(employeeScheduleDTO);
        }
        weeklyScheduleDTO.setSchedules(employeeScheduleDTOS);

        return weeklyScheduleDTO;
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





