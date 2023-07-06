package com.laarc.hoamanagerserver.api.module.employee.controller;

import com.laarc.hoamanagerserver.api.dto.employee.EmployeeClockActivityDTO;
import com.laarc.hoamanagerserver.api.module.employee.service.EmployeeClockService;
import com.laarc.hoamanagerserver.api.module.security.utility.AccessControl;
import com.laarc.hoamanagerserver.shared.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employee-clock")
@RequiredArgsConstructor
public class EmployeeClockController {

    private final EmployeeClockService employeeClockService;

    @PreAuthorize(AccessControl.ADMINISTRATION)
    @PostMapping
    public EmployeeClockActivityDTO postEmployeeActivity(@RequestBody EmployeeClockActivityDTO activityDTO, Authentication authentication) {
        User employeeUser = (User) authentication.getDetails();
        activityDTO.setEmployeeId(employeeUser.getUserId());
        return employeeClockService.mapToDTO(employeeClockService.postActivity(activityDTO));
    }

    @PreAuthorize(AccessControl.RESTRICTED)
    @GetMapping("/employee/{employeeId}")
    public List<EmployeeClockActivityDTO> getEmployeeTimesheet(@PathVariable("employeeId") Long employeeId, @RequestParam("date") LocalDate date) {

        return employeeClockService.getEmployeeTimesheet(employeeId, date)
                .stream()
                .map(employeeClockService::mapToDTO)
                .toList();

    }

}
