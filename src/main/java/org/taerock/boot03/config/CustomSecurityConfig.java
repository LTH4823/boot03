package org.taerock.boot03.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.taerock.boot03.security.CustomUserDetailsService;
import org.taerock.boot03.security.handler.Custom403Handler;
import org.taerock.boot03.security.handler.CustomSocialLoginSuccessHandler;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Log4j2
@Configuration
@RequiredArgsConstructor
public class CustomSecurityConfig {

    private final DataSource dataSource;
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${spring.websecurity.debug:false}")
    boolean webSecurityDebug;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // handler bean 추가
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomSocialLoginSuccessHandler(passwordEncoder());
    }

    // 모든 요청에 대한 처리 필터
    @Bean
    public SecurityFilterChain filterChain(final HttpSecurity http) throws Exception {
        log.info("======================================");
        log.info("======================================");
        log.info("=======CustomSecurityConfig ==========");
        log.info("======================================");
        log.info("======================================");

        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/member/**", "/board/list", "/view/**").permitAll()
                        .requestMatchers("/board/register").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exc -> exc.accessDeniedHandler(accessDeniedHandler()))
                .formLogin((form) -> form.loginPage("/member/login").successHandler(authenticationSuccessHandler()))
                .oauth2Login(oath -> oath.successHandler(authenticationSuccessHandler()))
                .csrf((csrf) -> csrf.disable())
                .rememberMe((remember) -> remember
                        .key("12345678")
                        .tokenRepository(persistentTokenRepository())
                        .userDetailsService(customUserDetailsService)
                        .tokenValiditySeconds(60 * 60 * 24 * 30)

                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        log.info("------------web configure-------------------");
        return (web) -> web
                .debug(webSecurityDebug)
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    //jdbc 관련 설정
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new Custom403Handler();
    }

}
