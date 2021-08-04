package com.neconico.neconicojpa.repository.member;

import com.neconico.neconicojpa.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("select m from Member m where m.accountId =:id and m.password =:pw")
    Optional<Member> findMemberByAccountIdAndAccountPw(@Param("id") String accountId, @Param("pw") String accountPw);

    Optional<Member> findMemberByAccountId(String accountId);

}
