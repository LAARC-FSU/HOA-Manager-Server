package com.laarc.hoamanagerserver.api.module.employee.repository;

import com.laarc.hoamanagerserver.shared.model.EmployeeHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeHoursRepository extends JpaRepository<EmployeeHours, Long> {

    @Query("SELECT h from EmployeeHours h where h.employeeUser.userId = :id and casat(h.timeIn) LIKE :tin ")
    List<EmployeeHours> employeeToday(Long id, LocalDate tin);
}
