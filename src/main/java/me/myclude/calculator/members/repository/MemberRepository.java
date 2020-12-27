package me.myclude.calculator.members.repository;

import me.myclude.calculator.members.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

     Optional<Member> findByEmployeeNumber(String employeeNumber);

     Page<Member> findAll(Pageable pageable);
}
