package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원이 저장소에 저장됨
    Optional<Member> findById(Long id); //찾아옴 Id로
    Optional<Member> findByName(String name);   //Name으로 찾아옴
    List<Member> findAll(); //지금 까지 저장된 모든 회원 반환


}
