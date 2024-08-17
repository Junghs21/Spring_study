package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional  //해당 어노테이션때문에 테스트가 끝나고 데이터가 롤백이 되므로 로그 출력 화면을 보면 DB에 insert문이 나가지 않는 것을 확인할 수 있다.(해당 어노테이션은 테스트 케이스에서 사용될 때 기본적으로 롤백을 제공하지 Service나 Repository 클래스에 붙이면 롤백을 제공하지 않는다.)
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    //@Rollback(value = false) //이렇게 하면 테스트가 끝나고 롤백하지 않으므로 DB에 테스트 데이터가 반영된 것을 확인할 수 있다.
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        //em.flush(); //이걸 적으면 롤백이 되지만 insert문을 강제로 확인할 수 있다.
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);    //요기서 예외가 발생해야 함

        //then
        fail("예외가 발생해야 한다.");
    }
}