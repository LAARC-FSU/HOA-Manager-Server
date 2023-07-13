package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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

    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime time;

    @Enumerated(EnumType.STRING)
    private EmployeeClockActivityType activityType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_user_id", referencedColumnName = "user_id")
    private User employeeUser;

    @Temporal(value = TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime created;

}
