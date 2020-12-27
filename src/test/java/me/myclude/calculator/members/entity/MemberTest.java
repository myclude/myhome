package me.myclude.calculator.members.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    @DisplayName("빌드 테스트")
    public void BuildTest() {
        
        //given
        Member member = Member.builder()
                .employeeNumber("5800871")
                .username("beans")
                .email("nk.sung@gmail.com")
                .password("1234")
                .deptCode("SDSR")
                .phoneNumber("02-6296-4485")
                .language("ko")
                .build();

        System.out.println(member.getPassword());

        assertThat(member.getUsername()).isEqualTo("beans");
    }
}