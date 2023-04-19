package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class DeedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deedDocumentId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "uploader_user_id")
    private User uploader;

    @NotNull
    private String fileName;

    @CreationTimestamp
    private LocalDate uploaded;

    @UpdateTimestamp
    private LocalDate lastUpdated;

}
