package me.myclude.calculator.members.controller;

import me.myclude.calculator.common.BaseControllerTest;
import me.myclude.calculator.members.entity.Role;
import me.myclude.calculator.members.service.MemberService;
import me.myclude.calculator.members.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MemberControllerTest extends BaseControllerTest {

    @Autowired
    MemberService memberService;

    @Autowired
    RoleService roleService;

//    @BeforeEach
//    public void addRole() {
//
//        Role role = Role.builder()
//                .name("ADMIN")
//                .build();
//
//        Role role2 = Role.builder()
//                .name("USER")
//                .build();
//
//        roleService.save(role);
//        roleService.save(role2);
//
//    }

    @Test
    @WithUserDetails(value = "admin")
    @DisplayName("샘플 테스트")
    public void sampleTestDo() throws Exception {

//        MemberDto dto = MemberDto.builder()
//                .username("테스트")
//                .employeeNumber("A12345")
//                .deptCode("TKMB")
//                .email("memberTest@Email.com")
//                .password("test")
//                .language("KO")
//                .phoneNumber("010-1111-2222")
//                .enableString("Y")
//                .adminChecked(true)
//                .build();
//
//        Member savedMember = memberService.save(dto);

//        mockMvc.perform(post("/member/memberform")
//                .content(objectMapper.writeValueAsBytes(dto)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                ;

        mockMvc.perform(get("/member/list"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}