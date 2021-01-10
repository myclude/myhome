package me.myclude.calculator.members.repository;

import me.myclude.calculator.members.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String string);
}
