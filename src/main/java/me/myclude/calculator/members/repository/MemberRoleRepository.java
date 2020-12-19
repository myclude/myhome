package me.myclude.calculator.members.repository;

import me.myclude.calculator.members.entity.MemberRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRoleRepository extends JpaRepository<MemberRole, Long> {
}
