package com.laarc.hoamanagerserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String deedDocumentId;

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] data;

    @NotNull
    private String fileName;

}
