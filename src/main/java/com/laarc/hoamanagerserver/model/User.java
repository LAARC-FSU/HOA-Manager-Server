package com.laarc.hoamanagerserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;

    @NotNull
    @Column(unique = true)
    private String email;
    @ManyToOne
    @JoinColumn(name = "user_role_id", nullable = false)
    private UserRole userRole;
    @NotNull
    private String password;
}
