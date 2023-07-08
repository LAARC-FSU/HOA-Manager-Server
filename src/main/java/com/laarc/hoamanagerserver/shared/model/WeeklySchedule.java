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

    @Column(name = "time_frame_str")
    private String timeFrame;

    @OneToOne
    @JoinColumn(name = "shift_id")
    private Shift shiftId;

    private boolean posted;
}
