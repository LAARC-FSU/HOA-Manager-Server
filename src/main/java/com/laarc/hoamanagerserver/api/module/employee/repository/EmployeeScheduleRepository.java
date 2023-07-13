package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.EmployeeSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, Long> {
    List<EmployeeSchedule> findByScheduleId(Long scheduleId);
}
