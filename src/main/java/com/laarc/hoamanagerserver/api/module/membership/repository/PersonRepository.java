package com.laarc.hoamanagerserver.api.module.membership.repository;

import com.laarc.hoamanagerserver.shared.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
