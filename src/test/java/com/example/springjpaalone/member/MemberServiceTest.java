package com.example.springjpaalone.member;

import com.example.springjpaalone.member.entity.Member;
import com.example.springjpaalone.member.repository.MemberRepository;
import com.example.springjpaalone.member.service.MemberService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() {

        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.joinMember(member);

        em.flush();
        Assertions.assertEquals(member, memberRepository.findOne(saveId));

    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원조회() {

        Member member = new Member();
        member.setName("kim");

        memberService.joinMember(member);

        Member newMember = new Member();
        newMember.setName("kim");

        memberService.joinMember(newMember);
        //오류발생해야함

        fail("예외가 발생해야합니다");
    }
}
