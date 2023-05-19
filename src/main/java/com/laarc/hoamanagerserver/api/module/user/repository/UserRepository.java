package com.laarc.hoamanagerserver.api.module.user.repository;

import com.laarc.hoamanagerserver.shared.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findUserByEmail(String email);

}
