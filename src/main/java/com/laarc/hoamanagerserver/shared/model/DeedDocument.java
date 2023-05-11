package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class DeedDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deedDocumentId;

    @ManyToOne
    @JoinColumn(name = "uploader_user_id")
    private User uploader;

    @NotNull
    private String fileName;

    @CreationTimestamp
    private LocalDateTime uploaded;

    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setUniqueFileName() {

        String uuid = UUID.randomUUID().toString();

        String fileNameWithoutExt = fileName.substring(0, fileName.indexOf("."));
        String extension = fileName.substring(fileName.indexOf("."));
        this.fileName = fileNameWithoutExt + "_" + uuid + extension;

    }

}
