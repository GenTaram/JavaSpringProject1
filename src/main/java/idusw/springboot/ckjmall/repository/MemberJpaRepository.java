package idusw.springboot.ckjmall.repository;

import idusw.springboot.ckjmall.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
    //Optional : null 문제를 해결하기 위해서 정의한 프로그래밍 요소
    Optional<MemberEntity> findByIdAndPw(String id, String pw);
}
