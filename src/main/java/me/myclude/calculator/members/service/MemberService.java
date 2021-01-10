package me.myclude.calculator.members.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import me.myclude.calculator.members.dto.MemberDto;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.entity.MemberRole;
import me.myclude.calculator.members.entity.Role;
import me.myclude.calculator.members.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRoleService memberRoleService;
    private final RoleService roleService;

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
    public Member save(MemberDto memberDto) {
        //
    	
    	List<MemberRole> roles = new ArrayList<MemberRole>();
    	Boolean enabled = true;
    	
    	//사용여부
    	if(memberDto.getEnableString().equals("N")) {
    		enabled = false;
    	}

    	Member member = Member.builder()
    			.username(memberDto.getUsername())
    			.employeeNumber(memberDto.getEmployeeNumber())
    			.password(passwordEncoder.encode(memberDto.getPassword()))
    			.deptCode(memberDto.getDeptCode())
    			.email(memberDto.getEmail())
    			.language(memberDto.getLanguage())
    			.phoneNumber(memberDto.getPhoneNumber())
    			.enabled(enabled)
    			.build();
    	
    	System.out.println(member.toString());
    	
    	Member savedMember = memberRepository.save(member);
    	
    	if(memberDto.isAdminChecked()) {
    		
    		Role adminRole = roleService.findByName("ADMIN");
    		MemberRole adminMemberRole = MemberRole.createMember(savedMember, adminRole);
    		
    		MemberRole savedAdminMemberRole = memberRoleService.save(adminMemberRole);
    		roles.add(savedAdminMemberRole);
    	}
    	
    	if(memberDto.isUserChecked()) {
    		
    		Role userRole = roleService.findByName("USER");
    		MemberRole userMemberRole = MemberRole.createMember(savedMember, userRole);
    		
    		MemberRole savedUserRole = memberRoleService.save(userMemberRole);
    		roles.add(savedUserRole);
    	}
    	
    	savedMember.updateRoles(roles);
    	
        return savedMember;

    }

    /**
     *  회원 조회
     */
    public Page<Member> findAll(Pageable pageable) {
        //
        return memberRepository.findAll(pageable);
    }
    
    public Optional<Member> findById(Long id) {
    	//
    	return memberRepository.findById(id);
    }

	public Optional<Member> searchById(Long id) {
		//
		return memberRepository.searchById(id);
	}
}
