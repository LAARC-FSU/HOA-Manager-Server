package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftId;

    private Long firstShift;
    private Long secondShift;
    private Long thirdShift;
}
