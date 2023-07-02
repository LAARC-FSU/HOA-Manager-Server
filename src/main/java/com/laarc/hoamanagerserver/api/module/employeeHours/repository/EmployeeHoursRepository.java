package com.laarc.hoamanagerserver.api.module.employeeHours.repository;

import com.laarc.hoamanagerserver.shared.model.EmployeeHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeHoursRepository extends JpaRepository<EmployeeHours, Long> {

    @Query("SELECT h from EmployeeHours h where h.app_user.userId = :id and casat(h.timeIn) LIKE :tin ")
    List<EmployeeHours> employeeToday(Long id, LocalDate tin);
    EmployeeHours create(EmployeeHours employeeHours);

    void update(EmployeeHours employeeHours, Long id);

    void delete(Long id);

    Optional<EmployeeHours> findById(Long id);
}
