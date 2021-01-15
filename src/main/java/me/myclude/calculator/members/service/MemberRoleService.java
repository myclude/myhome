package me.myclude.calculator.members.service;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.entity.MemberRole;
import me.myclude.calculator.members.repository.MemberRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberRoleService {

    private final MemberRoleRepository memberRoleRepository;

    @Transactional
    public MemberRole save(MemberRole memberRole) {
        return memberRoleRepository.save(memberRole);
    }

    @Transactional
    public void deleteByMember(Member member) {
        memberRoleRepository.deleteByMember(member);
    }
}
