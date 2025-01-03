package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.RegularUser;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

@Controller
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/mainBeforeRegular")
    public String home(@AuthenticationPrincipal RegularUser regularUser, HttpSession session) {
        if (regularUser != null) {
            logger.info(regularUser.getRole());
            session.setAttribute("idx", regularUser.getIdx());
            session.setAttribute("id", regularUser.getUser_id());
            session.setAttribute("role", regularUser.getRole());
        }
        return "redirect:/main";
    }

    @GetMapping("/mainBeforeBusiness")
    public String home(@AuthenticationPrincipal BusinessUser businessUser, HttpSession session) {
        if (businessUser != null) {
            logger.info(businessUser.getRole());
            session.setAttribute("idx", businessUser.getBusiness_idx());
            session.setAttribute("business_id", businessUser.getBusiness_id());
            session.setAttribute("business_number", businessUser.getBusiness_number());
            session.setAttribute("role", businessUser.getRole());
        }
        return "redirect:/main";
    }


    @GetMapping(value = "/main")
    public String main() {
        return "home/home";
    }
}
