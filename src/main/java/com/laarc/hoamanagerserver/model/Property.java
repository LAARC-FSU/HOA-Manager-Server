package com.laarc.hoamanagerserver.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Property {
    @Id
    private Long propertyId;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}
