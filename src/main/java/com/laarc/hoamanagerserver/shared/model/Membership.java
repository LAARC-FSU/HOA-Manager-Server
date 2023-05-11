package com.laarc.hoamanagerserver.shared.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Membership {
    @Id
    private String membershipId;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private MembershipStatus status;

    @OneToMany(mappedBy = "membership")
    @OrderColumn(name = "membership_index")
    private List<Member> members;

    @ManyToOne
    @JoinColumn(name = "primary_member_id")
    private Member primaryMember;

    @OneToMany
    @JoinTable(
            name = "membership_properties",
            joinColumns = @JoinColumn(name = "membership_id"),
            inverseJoinColumns = @JoinColumn(name = "property_id")
    )
    private Set<Property> properties;

    @OneToMany
    @JoinTable(
            name = "membership_payments",
            joinColumns = @JoinColumn(name = "membership_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_id")
    )
    private List<Payment> payments;

}
