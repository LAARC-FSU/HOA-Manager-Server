package com.laarc.hoamanagerserver.api.module.membership.service;

import com.laarc.hoamanagerserver.api.dto.address.AddressDTO;
import com.laarc.hoamanagerserver.api.dto.membership.MemberDTO;
import com.laarc.hoamanagerserver.api.dto.membership.MembershipResponse;
import com.laarc.hoamanagerserver.api.dto.membership.PostMembership;
import com.laarc.hoamanagerserver.api.module.membership.repository.AddressRepository;
import com.laarc.hoamanagerserver.api.module.membership.repository.MemberRepository;
import com.laarc.hoamanagerserver.api.module.membership.repository.MembershipRepository;
import com.laarc.hoamanagerserver.api.module.membership.repository.PersonRepository;
import com.laarc.hoamanagerserver.api.module.membership.util.MembershipIdGenerator;
import com.laarc.hoamanagerserver.api.module.property.service.PropertyService;
import com.laarc.hoamanagerserver.exception.http.BadRequestException;
import com.laarc.hoamanagerserver.exception.http.NotFoundException;
import com.laarc.hoamanagerserver.shared.model.Address;
import com.laarc.hoamanagerserver.shared.model.AddressType;
import com.laarc.hoamanagerserver.shared.model.Member;
import com.laarc.hoamanagerserver.shared.model.Membership;
import com.laarc.hoamanagerserver.shared.model.MembershipStatus;
import com.laarc.hoamanagerserver.shared.model.Person;
import com.laarc.hoamanagerserver.shared.model.Property;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipIdGenerator membershipIdGenerator;
    private final MembershipRepository membershipRepository;
    private final MemberRepository memberRepository;
    private final PersonRepository personRepository;
    private final AddressRepository addressRepository;
    private final PropertyService propertyService;
    private final ModelMapper modelMapper;

    @Transactional
    public Membership createMembership(PostMembership postMembership) {

        Membership membership = modelMapper.map(postMembership, Membership.class);

        // Generate membership ID
        String membershipId = membershipIdGenerator.generateId();
        log.info("Membership ID: {}", membershipId);
        membership.setMembershipId(membershipId);
        // Set status to ACTIVE
        membership.setStatus(MembershipStatus.ACTIVE);

        membership = membershipRepository.save(membership);

        // Find primary member in list (First index is primary member)
        MemberDTO primaryMemberDTO = postMembership.getMembers().stream()
                .filter(MemberDTO::isPrimaryMember)
                .findFirst()
                .orElseThrow(() -> new BadRequestException("No members have been marked as primary."));

        Member primaryMember = modelMapper.map(primaryMemberDTO, Member.class);
        primaryMember.setMembership(membership);

        saveMemberPerson(primaryMemberDTO, primaryMember);
        primaryMember = memberRepository.save(primaryMember);

        // Save all other members
        Membership finalMembership = membership;
        List<Member> members = postMembership.getMembers().stream()
                .skip(1) // Skip first one as that is the primary member
                .map(memberDTO -> {
                    Member member = modelMapper.map(memberDTO, Member.class);
                    saveMemberPerson(memberDTO, member);
                    member.setMembership(finalMembership);
                    return memberRepository.save(member);
                })
                .collect(Collectors.toList());
        members.add(primaryMember);

        // Save all properties
        Set<Property> properties = postMembership.getProperties().stream()
                .map(propertyService::createProperty)
                .collect(Collectors.toSet());

        // Set primary member
        membership.setPrimaryMember(primaryMember);
        // Set properties
        membership.setProperties(properties);
        // Set members
        membership.setMembers(members);

        return membershipRepository.save(membership);
    }

    private void saveMemberPerson(MemberDTO memberDTO, Member member) {
        Address address = addressRepository.save(modelMapper.map(memberDTO.getMailingAddress(), Address.class));

        Set<Address> addresses = new HashSet<>();
        addresses.add(address);

        Person person = personRepository.save(Person.builder()
                .firstName(memberDTO.getFirstName())
                .middleName(memberDTO.getMiddleName())
                .lastName(memberDTO.getLastName())
                .suffix(memberDTO.getSuffix())
                .addresses(addresses)
                .lastUpdated(LocalDateTime.now())
                .created(LocalDateTime.now())
                .build());

        member.setPerson(person);
    }

    public MembershipResponse mapToResponse(Membership membership) {
        return MembershipResponse.builder()
                .membershipId(membership.getMembershipId())
                .members(membership.getMembers().stream()
                        .map(this::mapMemberToMemberDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    private MemberDTO mapMemberToMemberDTO(Member member) {
        Person person = member.getPerson();
        Address mailingAddress = person.getAddresses().stream()
                .filter(address -> address.getAddressType() == AddressType.MAILING)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Mailing address was not found for person %s.".formatted(person.getPersonId())));
        AddressDTO addressDTO = modelMapper.map(mailingAddress, AddressDTO.class);
        boolean primaryMember = Objects.equals(member.getMembership().getPrimaryMember().getMemberId(), member.getMemberId());
        return MemberDTO.builder()
                .memberId(member.getMemberId())
                .firstName(person.getFirstName())
                .middleName(person.getMiddleName())
                .lastName(person.getLastName())
                .suffix(person.getSuffix())
                .mailingAddress(addressDTO)
                .primaryMember(primaryMember)
                .build();
    }

}
