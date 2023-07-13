package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.ShiftTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftTimeRepository extends JpaRepository<ShiftTime, Long> {
}
