package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.dto.employee.WeeklyScheduleDTO;
import com.laarc.hoamanagerserver.api.module.employee.repository.*;
import com.laarc.hoamanagerserver.shared.model.WeeklySchedule;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WeeklyScheduleService {

    private final WeeklyScheduleRepository weeklyScheduleRepository;
    private final ModelMapper modelMapper;

    public WeeklyScheduleService(WeeklyScheduleRepository weeklyScheduleRepository, ModelMapper modelMapper) {
        this.weeklyScheduleRepository = weeklyScheduleRepository;
        this.modelMapper = modelMapper;
    }

    public WeeklySchedule saveWeeklySchedule(WeeklyScheduleDTO weeklyScheduleDTO) {
        WeeklySchedule weeklySchedule = modelMapper.map(weeklyScheduleDTO, WeeklySchedule.class);
        return weeklyScheduleRepository.save(weeklySchedule);
    }
    public WeeklySchedule saveWeeklySchedule(WeeklySchedule weeklySchedule) {
        return weeklyScheduleRepository.save(weeklySchedule);
    }

    public List<WeeklySchedule> getWeeklySchedule() {
        return weeklyScheduleRepository.findAll();
    }

    public Optional<WeeklySchedule> update(Long id) {
        return weeklyScheduleRepository.findById(id);
    }
   public void delete(Long id){
        weeklyScheduleRepository.deleteById(id);
   }
}
