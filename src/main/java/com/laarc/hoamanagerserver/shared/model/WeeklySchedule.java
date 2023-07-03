package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class WeeklySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long weeklyScheduleId;

    private String timeFrame;

    @OneToOne
    @JoinColumn(name = "time_frame_id")
    private TimeFrame timeFrameId;

    @OneToOne
    @JoinColumn(name = "shift_id")
    private Shift shiftId;

    private boolean posted;
}
