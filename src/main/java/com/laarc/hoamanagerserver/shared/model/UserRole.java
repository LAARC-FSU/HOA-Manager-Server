package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {

    public static final UserRole ADMINISTRATOR = new UserRole(1, UserRoleName.ADMINISTRATOR);
    public static final UserRole EMPLOYEE = new UserRole(2, UserRoleName.EMPLOYEE);
    public static final UserRole MEMBER = new UserRole(3, UserRoleName.MEMBER);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userRoleId;

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private UserRoleName roleName;

}
