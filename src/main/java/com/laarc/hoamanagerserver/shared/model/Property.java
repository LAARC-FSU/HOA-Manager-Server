package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propertyId;

    @OneToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

//    @OneToOne
//    @JoinColumn(name = "deed_document_id", nullable = false)
//    private DeedDocument deedDocument;

    private String lotArea;
    private String lotFrontage;
    private String lotDepth;
    private String subdivision;
    private Integer block;
    private String zoning;
    private String zoningMap;
    private Integer numberOfBuildings;
    private Integer numberOfFloors;
    private Integer unit;
    private String lot;
}
