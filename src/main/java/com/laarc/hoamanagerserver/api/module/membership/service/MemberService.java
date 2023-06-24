package com.laarc.hoamanagerserver.api.module.membership.service;

import com.laarc.hoamanagerserver.api.dto.user.PostUser;
import com.laarc.hoamanagerserver.api.dto.user.RegisterMemberUser;
import com.laarc.hoamanagerserver.api.dto.user.UserResponse;
import com.laarc.hoamanagerserver.api.module.membership.repository.MemberRepository;
import com.laarc.hoamanagerserver.api.module.membership.repository.MembershipRepository;
import com.laarc.hoamanagerserver.api.module.user.service.UserService;
import com.laarc.hoamanagerserver.exception.http.BadRequestException;
import com.laarc.hoamanagerserver.exception.http.NotFoundException;
import com.laarc.hoamanagerserver.shared.model.Member;
import com.laarc.hoamanagerserver.shared.model.Membership;
import com.laarc.hoamanagerserver.shared.model.User;
import com.laarc.hoamanagerserver.shared.model.UserRoleName;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembershipRepository membershipRepository;
    private final UserService userService;

    @Transactional
    public User registerMemberUser(@Valid RegisterMemberUser registerMemberUser) {

        String[] membershipNumber = registerMemberUser.getMembershipId().split("-");
        String membershipId = membershipNumber[0];
        int memberIndex = Integer.parseInt(membershipNumber[1]);

        Membership membership = membershipRepository.findById(membershipId)
                .orElseThrow(() -> new NotFoundException("Membership ID is not valid."));

        if (memberIndex >= membership.getMembers().size()) {
            throw new NotFoundException("Member does not exist.");
        }

        Member member = membership.getMembers().get(memberIndex);

        PostUser postUser = PostUser.builder()
                .email(registerMemberUser.getEmail())
                .password(registerMemberUser.getPassword())
                .userRoleName(UserRoleName.MEMBER)
                .build();

        User user = userService.createUser(postUser);
        member.setUser(user);
        memberRepository.save(member);

        return user;

    }

}
