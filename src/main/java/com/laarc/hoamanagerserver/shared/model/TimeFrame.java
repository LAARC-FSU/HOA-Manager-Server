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

    private String time1;
    private String time2;
    private String time3;
    private String time4;
    private String time5;
    private String time6;
    private String time7;

}
