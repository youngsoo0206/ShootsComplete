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

    @GetMapping("/mainBefore") //로그인이 성공하면 main 주소로 가기 전 로그인 유저 타입을 확인하는 경로
    public String home(@AuthenticationPrincipal Object principal, HttpSession session) {
        if (principal instanceof RegularUser) {
            RegularUser regularUser = (RegularUser) principal;
        } else if (principal instanceof BusinessUser) {
            BusinessUser businessUser = (BusinessUser) principal;
        }
        return "redirect:/main";
    }

    @GetMapping(value = "/main")
    public String main() {
        return "home/home";
    }
}
