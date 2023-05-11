package com.laarc.hoamanagerserver.api.module.membership.repository;

import com.laarc.hoamanagerserver.shared.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
