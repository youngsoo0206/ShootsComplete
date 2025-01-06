package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.RegularUser;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class HomeController {
    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/mainBefore") //로그인이 성공하면 main 주소로 가기 전 로그인 유저 타입을 확인하는 경로
    public void home(@AuthenticationPrincipal Object principal, HttpSession session, HttpServletResponse response) throws IOException {

        if (principal instanceof RegularUser) {
            RegularUser regularUser = (RegularUser) principal;
        } else if (principal instanceof BusinessUser) {
            BusinessUser businessUser = (BusinessUser) principal;
        }

        if ("pending".equals(session.getAttribute("businessAccess"))) {
            response.setContentType("text/html; charset=utf-8");
            response.setCharacterEncoding("utf-8");

            PrintWriter out = response.getWriter();
            out.println("<script type='text/javascript'>");
            out.println("if(confirm('미승인된 기업입니다')){");
            out.println("location.href='/Shoots/logout';");
            out.println("} else { location.href='/Shoots/logout'; }");
            out.println("</script>");
            out.flush();
        }
        response.sendRedirect("/Shoots/main");

    }

    @GetMapping(value = "/main")
    public String main() {
        return "home/home";
    }
}
