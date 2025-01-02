package com.Shoots.controller;

import com.Shoots.domain.RegularUser;
import com.Shoots.security.CustomUserDetailsService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/mainBefore")
    public String home(@AuthenticationPrincipal RegularUser regularUser, HttpSession session) {
        if (regularUser != null) {
            logger.info(regularUser.getRole());
            session.setAttribute("idx", regularUser.getIdx());
            session.setAttribute("id", regularUser.getUser_id());
            session.setAttribute("role", regularUser.getRole());
        }
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main() {
        return "home/home";
    }
}
