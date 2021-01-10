package me.myclude.calculator.members.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import me.myclude.calculator.members.dto.MemberDto;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.entity.MemberRole;
import me.myclude.calculator.members.entity.Role;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    RoleService roleService;

    @Autowired
    MemberRoleService memberRoleService;

    @Test
    @DisplayName("계정이 정상적으로 생성이 되는가?")
    public void findByUsername()  {

        //given
        String password = "1234";
        String username = "5800871";

        Role role = Role.builder()
                .name("ADMIN")
                .build();

        Role saveRole = roleService.save(role);
        
        MemberDto memberDto = MemberDto.builder()
                .username("naggeon")
                .email("nk.sung@gmail.com")
                .employeeNumber(username)
                .deptCode("SDSR")
                .language("KO")
                .password(password)
                .phoneNumber("010-0000-0000")
                .build();

        Member saveMember = memberService.save(memberDto);

        MemberRole memberRole = MemberRole.createMember(saveMember, saveRole);

        memberRoleService.save(memberRole);

        //when
        UserDetailsService userDetailsService = memberService;
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //then
        assertThat(userDetails.getPassword()).isEqualTo(password);
    }


    @Test
    @DisplayName("유저가 없을 경우 제대로 생성이 되는가?")
    public void findByUsernameFail() {

        //given
        String employeeName = "50002120";

        //when
        //then
        UsernameNotFoundException exceptions = assertThrows(UsernameNotFoundException.class, () -> memberService.loadUserByUsername(employeeName));

        assertThat(exceptions.getMessage()).containsSequence(employeeName);
    }

}