package com.laarc.hoamanagerserver.shared.model;

import jakarta.annotation.Nullable;
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

    @Nullable
    @Column(name = "first_Shift")
    private Long firstShift;

    @Nullable
    @Column(name = "second_Shift")
    private Long secondShift;

    @Nullable
    @Column(name = "third_Shift")
    private Long thirdShift;

}
