package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TimeFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long timeFrameId;
    private Long Id;
    private String time;
}
