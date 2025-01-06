package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.RegularUser;
import com.Shoots.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/business")
public class BusinessController {

    private PaymentService paymentService;

    public BusinessController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/dashboardBefore") //로그인이 성공하면 main 주소로 가기 전 로그인 유저 타입을 확인하는 경로
    public String beforeBusinessDashboard(@AuthenticationPrincipal Object principal, HttpSession session) {
        if (principal instanceof BusinessUser) {
            BusinessUser businessUser = (BusinessUser) principal;
        }

        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String businessDashboard() {
        return "business/dashboard";
    }

    @GetMapping("/post")
    public String businessPost() {
        return "business/post";
    }
}
