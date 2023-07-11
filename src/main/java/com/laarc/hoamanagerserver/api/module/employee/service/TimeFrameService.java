package com.laarc.hoamanagerserver.api.module.employee.service;

import com.laarc.hoamanagerserver.api.module.employee.repository.TimeFrameRepository;
import com.laarc.hoamanagerserver.shared.model.TimeFrame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeFrameService {
    private final TimeFrameRepository timeFrameRepository;

    public TimeFrameService(TimeFrameRepository timeFrameRepository) {
        this.timeFrameRepository = timeFrameRepository;
    }
    public TimeFrame saveTime(TimeFrame timeFrame) {
        return timeFrameRepository.save(timeFrame);
    }

    public List<TimeFrame> getTimeFrames(Long weeklyScheduleId){return timeFrameRepository.findByWeeklyScheduleId(weeklyScheduleId);}
}
