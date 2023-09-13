package org.taerock.boot03.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
// 해당 구조를 빈으로 할지 서비스로 할지 이 설정이 까다롭습니다.
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    // 우클릭 - Generate - loadUser를 Overriede 해주시면 됩니다.
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        log.info("=================================");
        log.info("=================================");
        log.info(userRequest);
        log.info("=================================");
        log.info("=================================");

        return super.loadUser(userRequest);
    }

}
