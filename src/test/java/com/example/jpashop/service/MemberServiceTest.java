package com.example.jpashop.service;

import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Rollback(value = false)
    public void 회원가입() {
        //given
        Member member = new Member();
        member.setName("kim");
        //when
        Long saveid = memberService.join(member);

        //then
        assertEquals(member,memberRepository.findOne(saveid));
    }

    @Test
    public void 중복회원예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //when
        Long saveid1 = memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        //then
//        fail("exception occur");
    }

}