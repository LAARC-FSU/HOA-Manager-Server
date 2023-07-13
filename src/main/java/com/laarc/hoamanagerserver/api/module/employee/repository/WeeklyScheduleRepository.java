package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.WeeklySchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeeklyScheduleRepository extends JpaRepository<WeeklySchedule, Long> {
}
