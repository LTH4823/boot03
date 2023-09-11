package org.taerock.boot03.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Log4j2
public class MemberController {

    @GetMapping("/login")
    public void loginGET(String error, String logout){
        log.info("login get...................");
        log.info("logout: "+logout);
    }
}