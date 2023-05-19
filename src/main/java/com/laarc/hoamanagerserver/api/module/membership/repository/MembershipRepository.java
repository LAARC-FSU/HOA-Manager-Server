package com.laarc.hoamanagerserver.api.module.membership.repository;

import com.laarc.hoamanagerserver.shared.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipRepository extends JpaRepository<Membership, String> {
}
