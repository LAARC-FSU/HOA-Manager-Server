package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmployeeHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long DaysId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User app_user;
    @NotNull
    private LocalDateTime timeIn;

    private LocalDateTime timeOut;

    private LocalDateTime lunchStart;

    private LocalDateTime lunchEnd;
}
