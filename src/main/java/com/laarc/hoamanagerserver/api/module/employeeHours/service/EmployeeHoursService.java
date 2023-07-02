package com.laarc.hoamanagerserver.api.module.employeeHours.service;

import com.laarc.hoamanagerserver.shared.model.EmployeeHours;

import java.util.List;

public interface EmployeeHoursService {
    List<EmployeeHours> employeeToday();
}
