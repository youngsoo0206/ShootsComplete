package com.Shoots.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);



    @GetMapping (value = "/login")
    public ModelAndView login(ModelAndView mv, @CookieValue(value = "remember-me", required = false) Cookie readCookie,
                              HttpSession session, Principal userPrincipal){
        if (readCookie != null) {
            logger.info("저장된 아이디 : " + userPrincipal.getName());
            mv.setViewName("redirect:/main");
        } else {
            mv.setViewName("home/loginForm");
            mv.addObject("loginResult", session.getAttribute("loginfail"));
            session.removeAttribute("loginfail");
        }

        return mv;
    }



}
