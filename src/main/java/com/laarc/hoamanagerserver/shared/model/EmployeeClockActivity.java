package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EmployeeClockActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    private LocalDateTime time;

    @Enumerated(EnumType.ORDINAL)
    private EmployeeClockActivityType activityType;

    @ManyToOne(optional = false)
    private User employeeUserId;
    
    @CreationTimestamp
    private LocalDateTime created;

}
