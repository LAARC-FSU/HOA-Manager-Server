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
    @JoinColumn(name = "first_shift_time_id")
    private ShiftTime firstShift;
    @OneToOne
    @JoinColumn(name = "second_shift_time_id")
    private ShiftTime secondShift;
    @OneToOne
    @JoinColumn(name = "third_shift_time_id")
    private ShiftTime thirdShift;

    private boolean enabled;
}
