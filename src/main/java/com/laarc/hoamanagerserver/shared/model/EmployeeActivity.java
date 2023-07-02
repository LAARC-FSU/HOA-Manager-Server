package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmployeeActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User employeeActivity;
    @NotNull
    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    private LocalDateTime lunchStart;

    private LocalDateTime lunchEnd;
}
