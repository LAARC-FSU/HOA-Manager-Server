package com.laarc.hoamanagerserver.api.module.membership.repository;

import com.laarc.hoamanagerserver.shared.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
