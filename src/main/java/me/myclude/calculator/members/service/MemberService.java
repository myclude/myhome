package me.myclude.calculator.members.service;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.entity.MemberRole;
import me.myclude.calculator.members.repository.MemberRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> optionalMem = memberRepository.findByEmployeeNumber(username);
        Member member = optionalMem.orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(member.getEmployeeNumber(), member.getPassword(), authorities(member.getRoles()));
    }

    private Collection<? extends GrantedAuthority> authorities(List<MemberRole> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole().getName()))
                .collect(Collectors.toSet());
    }

    /**
     * 회원 가입
     */
    @Transactional
    public Member save(Member member) {
        //
        return memberRepository.save(member);

    }
}
