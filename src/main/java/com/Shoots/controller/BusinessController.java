package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.Match;
import com.Shoots.domain.PaginationResult;
import com.Shoots.service.MatchService;
import com.Shoots.service.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/business")
public class BusinessController {

    private PaymentService paymentService;
    private MatchService matchService;

    public BusinessController(PaymentService paymentService, MatchService matchService) {
        this.paymentService = paymentService;
        this.matchService = matchService;
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
    public ModelAndView businessPost(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(required = false) String filter,
                                     @RequestParam(required = false) String gender,
                                     @RequestParam(required = false) String level,
                               ModelAndView modelAndView, HttpSession session) {

        Integer idx = (Integer) session.getAttribute("idx");

        session.setAttribute("refer", "list");

        int limit = 10;
        int listCount = matchService.getListCount();

        List<Match> list = matchService.getMatchListById(idx, filter, gender, level, page, limit);

        PaginationResult result = new PaginationResult(page, limit, listCount);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        for (Match match : list) {

            int playerCount = paymentService.getPlayerCount(match.getMatch_idx());
            match.setPlayerCount(playerCount);

            String formattedDate = match.getMatch_date().format(formatter);
            match.setFormattedDate(formattedDate);
        }

        modelAndView.setViewName("business/businessMatchPost");
        modelAndView.addObject("page", page);
        modelAndView.addObject("maxpage", result.getMaxpage());
        modelAndView.addObject("startpage", result.getStartpage());
        modelAndView.addObject("endpage", result.getEndpage());
        modelAndView.addObject("listcount", listCount);
        modelAndView.addObject("matchList", list);
        modelAndView.addObject("limit", limit);

        return modelAndView;
    }
}
