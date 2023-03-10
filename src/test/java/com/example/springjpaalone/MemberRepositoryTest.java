package com.example.springjpaalone;

import com.example.springjpaalone.member.entity.Member;
import com.example.springjpaalone.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class MemberRepositoryTest {

//    @Autowired
//    MemberRepository memberRepository;
//    @Test
//    @Transactional
//    @Rollback(false)
//    public void testMember() {
//        Member member = new Member();
//        member.setUsername("memberA");
//        Long savedId = memberRepository.save(member);
//        Member findMember = memberRepository.findOne(savedId);
//        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
//        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername())
//        ;
//        Assertions.assertThat(findMember).isEqualTo(member); //JPA 엔티티 동일성
//    }

}
