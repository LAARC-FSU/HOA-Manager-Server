package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftId;

    @OneToOne
    @JoinColumn(name = "shift_time_id")
    private ShiftTime firstShift;
    @OneToOne
    @JoinColumn(name = "shift_time_id")
    private ShiftTime secondShift;
    @OneToOne
    @JoinColumn(name = "shift_time_id")
    private ShiftTime thirdShift;

    private boolean enabled;
}
