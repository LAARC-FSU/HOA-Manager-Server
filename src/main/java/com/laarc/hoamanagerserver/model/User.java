package com.laarc.hoamanagerserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
    @Id
    @NotNull
    @Column(unique = true)
    private String userId;
    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;
    @NotNull
    private String password;
}
