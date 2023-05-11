package com.laarc.hoamanagerserver.api.module.property.repository;

import com.laarc.hoamanagerserver.shared.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
