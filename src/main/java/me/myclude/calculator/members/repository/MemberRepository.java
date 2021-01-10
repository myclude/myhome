package me.myclude.calculator.members.repository;

import me.myclude.calculator.members.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findById(Long id);
	Optional<Member> findByEmployeeNumber(String employeeNumber);

	Page<Member> findAll(Pageable pageable);
	
	@Query("select m from Member m join fetch m.roles r where m.memberId = :memberId")
	Optional<Member> searchById(@Param("memberId") Long id);
     
     
}
