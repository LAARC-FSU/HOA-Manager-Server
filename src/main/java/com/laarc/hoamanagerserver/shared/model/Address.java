package com.laarc.hoamanagerserver.shared.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

    @NotNull
    private String addressLine1;

    @Nullable
    private String addressLine2;

    @Nullable
    private String city;

    @Nullable
    private String state;

    @Nullable
    private String zipCode;

}
