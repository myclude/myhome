package me.myclude.calculator.members.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @Builder @AllArgsConstructor
@EqualsAndHashCode(of = "employeeNumber")
@SequenceGenerator(
        name = "MEMBER_SEQ_GENERATOR",
        sequenceName = "MEMBER_SEQ"
)
@Table(name = "PROMLUSR")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "EMPLOYEE_NUMBER", unique = true)
    private String employeeNumber;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "USER_NAME_LOC")
    private String username;

    @Column(name = "DEPT_CD")
    private String deptCode;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "LANG")
    private String language;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "ENABLED")
    private boolean enabled;

    @JsonIgnore
    @OneToMany(mappedBy = "member")
    private List<MemberRole> roles = new ArrayList<>();
}
