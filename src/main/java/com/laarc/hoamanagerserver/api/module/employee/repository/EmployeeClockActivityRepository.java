package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EmployeeClockActivityRepository extends JpaRepository<EmployeeClockActivity, Long> {

    List<EmployeeClockActivity> findEmployeeClockActivitiesByEmployeeUserUserIdAndTimeAfterAndTimeBefore(Long employeeUser_userId, LocalDateTime timeAfter, LocalDateTime timeBefore);

    EmployeeClockActivity findTopByEmployeeUserUserIdOrderByTimeDesc(Long employeeUser_userId);
}
