package org.taerock.boot03.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.taerock.boot03.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 소셜 로그인한 사람은 별도의 마킹 표시를 해주고 소셜로 들어지 않은 사람만 일단 허가 내는 방식
    @EntityGraph(attributePaths = "roleSet")
    @Query("select m from Member m where m.mid = :mid and m.social = false")
    Optional<Member> getWithRoles(String mid);

}
