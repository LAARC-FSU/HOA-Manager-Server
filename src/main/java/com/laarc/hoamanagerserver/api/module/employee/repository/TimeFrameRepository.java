package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeFrameRepository extends JpaRepository<TimeFrame, Long> {
    List<TimeFrame> findByWeeklyScheduleId(Long weeklyScheduleId);
}
