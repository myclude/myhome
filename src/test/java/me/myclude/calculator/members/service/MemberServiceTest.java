package me.myclude.calculator.members.service;

import me.myclude.calculator.members.dto.MemberDto;
import me.myclude.calculator.members.entity.Member;
import me.myclude.calculator.members.entity.MemberRole;
import me.myclude.calculator.members.entity.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Autowired
    PasswordEncoder passwordEncoder;

    @BeforeEach
    public void initRole() {

        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        roleService.save(role1);
        roleService.save(role2);

    }

    @Test
    @DisplayName("계정이 정상적으로 생성이 되는가?")
    public void findByUsername()  {

        //given
        String password = "1234";
        String username = "B12345";

        Role role = roleService.findByName("ADMIN");

        MemberDto memberDto = MemberDto.builder()
                .username("naggeon")
                .email("nk.sung@gmail.com")
                .employeeNumber(username)
                .deptCode("SDSR")
                .language("KO")
                .password(password)
                .phoneNumber("010-0000-0000")
                .enableString("Y")
                .build();

        Member saveMember = memberService.save(memberDto);
        MemberRole memberRole = MemberRole.createMember(saveMember, role);

        memberRoleService.save(memberRole);

        //when
        UserDetails userDetails = memberService.loadUserByUsername(username);

        //then
        assertThat(passwordEncoder.matches(password, userDetails.getPassword())).isTrue();
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