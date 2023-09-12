package org.taerock.boot03.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.taerock.boot03.domain.Member;
import org.taerock.boot03.repository.MemberRepository;
import org.taerock.boot03.security.dto.MemberSecurityDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    //PasswordEncoder를 직접적으로 주입한 방식
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberRepository memberRepository;

    public CustomUserDetailsService(){
        this.passwordEncoder = new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=========================================");
        log.info("================login====================");
        log.info(username);

        log.info(memberRepository.getWithRoles(username));
        Optional<Member> resultDate = memberRepository.getWithRoles(username);

        Member member = resultDate.orElseThrow(() -> new UsernameNotFoundException(username));

        log.info(member);

//        UserDetails result  = User.builder()
//                .username(username)
//                .password(passwordEncoder.encode("1111"))
//                .authorities("ROLE_USER")
//                .build();

        // dto 정보를 반환해서 처리하는 방식입니다.
        MemberSecurityDTO memberSecurityDTO =
                new MemberSecurityDTO(
                        member.getMid(),
                        member.getMpw(),
                        member.getEmail(),
                        member.isDel(),
                        false,
                        member.getRoleSet()
                                .stream().map(memberRole -> new SimpleGrantedAuthority("ROLE_" + memberRole.name()))
                                .collect(Collectors.toList())
                );

        return memberSecurityDTO;
    }
}
