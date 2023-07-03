package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ShiftTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftTimeId;
    private String start;
    private String end;
}
