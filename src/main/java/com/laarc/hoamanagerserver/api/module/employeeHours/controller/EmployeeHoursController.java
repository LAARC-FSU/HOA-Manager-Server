package com.laarc.hoamanagerserver.api.module.employeeHours.controller;

import com.laarc.hoamanagerserver.api.module.employeeHours.repository.EmployeeHoursRepository;
import com.laarc.hoamanagerserver.shared.model.EmployeeHours;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employeeHours")
public class EmployeeHoursController {

    private final EmployeeHoursRepository employeeHoursRepository;

    public EmployeeHoursController(EmployeeHoursRepository repository) {
        this.employeeHoursRepository = repository;
    }

    @GetMapping
    public List<EmployeeHours> findAll(){
        return employeeHoursRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeHours> findById(@PathVariable Long id){
        return employeeHoursRepository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeHours create(@RequestBody EmployeeHours employeeHours){
        return employeeHoursRepository.create(employeeHours);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody EmployeeHours employeeHours, @PathVariable Long id){
        employeeHoursRepository.update(employeeHours,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        employeeHoursRepository.delete(id);
    }
}
