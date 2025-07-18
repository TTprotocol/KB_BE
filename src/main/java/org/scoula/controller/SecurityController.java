package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Log4j2
@RequestMapping("/security")
@Controller
public class SecurityController {

    @GetMapping("/member")
    public void doMember(Principal principal) {
        log.info("username = " + principal.getName());
    }

    @GetMapping("/logout")
    public void logout() {
        log.info("logout page");
    }

    @GetMapping("/login")
    public void login() {
        log.info("login page");
    }

    @GetMapping("/all") // 모두 접근 가능
    public void doAll() {
        log.info("do all can access everybody");
    }


    @GetMapping("/admin") // ADMIN 권한 필요
    public void doAdmin() {
        log.info("admin only");
    }
}