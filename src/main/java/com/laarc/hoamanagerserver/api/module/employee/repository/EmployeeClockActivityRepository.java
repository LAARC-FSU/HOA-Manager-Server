package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.EmployeeClockActivity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeClockActivityRepository extends JpaRepository<EmployeeClockActivity, Long> {
}
