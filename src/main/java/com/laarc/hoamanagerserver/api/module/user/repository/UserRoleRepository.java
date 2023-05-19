package com.laarc.hoamanagerserver.api.module.user.repository;

import com.laarc.hoamanagerserver.shared.model.UserRole;
import com.laarc.hoamanagerserver.shared.model.UserRoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepository extends JpaRepository<UserRole, Short> {

    Optional<UserRole> findByRoleName(UserRoleName roleName);

}
