package me.myclude.calculator.members.entity;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class MemberTest {

    @Autowired
    EntityManager em;

    @Test
    void contextTest() {

        Member member = Member.builder()
                .username("5800871")
                .employeeNumber("5800871")
                .email("nk.sung@gmail.com")
                .language("KO")
                .enabled(true)
                .build();

        em.persist(member);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember qMember = new QMember("m");

        Member result = query
                .selectFrom(qMember)
                .fetchOne();

        assertThat(result).isEqualTo(member);

        if (result != null) {
            assertThat(result.getMemberId()).isEqualTo(member.getMemberId());
        }
    }
}