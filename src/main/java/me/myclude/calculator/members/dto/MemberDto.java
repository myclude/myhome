package me.myclude.calculator.members.dto;

import lombok.*;
import me.myclude.calculator.members.entity.MemberRole;

import java.util.List;

@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor @ToString(of = {"memberId", "employeeNumber", "password", "username", "deptCode", "deptName", 
		"email", "enableString", "adminChecked", "userChecked"})
public class MemberDto {

    private Long memberId;

    private String employeeNumber;

    private String password;

    private String username;

    private String deptCode;

    private String deptName;

    private String email;

    private String language;

    private String phoneNumber;
    
    private String enableString;
    
    private boolean adminChecked;
    
    private boolean userChecked;

    private List<MemberRole> roles;
    
    private List<Enabled> enabledCombo;

}
