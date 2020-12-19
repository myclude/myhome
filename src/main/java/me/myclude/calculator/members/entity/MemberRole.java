package me.myclude.calculator.members.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class MemberRole {

    @Id @GeneratedValue
    @Column(name = "memberRole_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;


    //생성 메서드
    public static MemberRole createMember(Member member, Role role) {

        MemberRole memberRole = new MemberRole();
        memberRole.setMember(member);
        memberRole.setRole(role);

        return memberRole;
    }
}
