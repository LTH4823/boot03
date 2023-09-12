package org.taerock.boot03.repository;

import lombok.extern.log4j.Log4j2;
import org.hibernate.metamodel.model.domain.internal.MapMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.taerock.boot03.domain.Member;
import org.taerock.boot03.domain.MemberRole;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemberRepositoryTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void selectMember(){
        String id="member90";
        Optional<Member> result = memberRepository.getWithRoles(id);
        log.info(result.get());
    }

    @Test
    public void insertMembers(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .mid("member"+i)
                    .mpw(passwordEncoder.encode("1111"))
                    .email("email"+i+"@aaa.bbb")
                    .build();

            member.addRole(MemberRole.USER);

            if(i >= 90){
                member.addRole(MemberRole.ADMIN);
            }
            memberRepository.save(member);

        });

    }


}
