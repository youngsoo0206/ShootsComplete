package com.Shoots.controller;

import com.Shoots.domain.*;
import com.Shoots.service.MatchService;
import com.Shoots.service.PaymentService;
import com.Shoots.service.RegularUserService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import static java.util.Locale.filter;


@Controller
@RequestMapping("/business")
public class BusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    private RegularUserService regularUserService;
    private PaymentService paymentService;
    private MatchService matchService;

    public BusinessController(RegularUserService regularUserService, PaymentService paymentService, MatchService matchService) {
        this.regularUserService = regularUserService;
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
        return "business/businessDashboard";
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
        int listCount = matchService.getListCountById(idx);

        List<Match> list = matchService.getMatchListById(idx, filter, gender, level, page, limit);

        PaginationResult result = new PaginationResult(page, limit, listCount);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

        for (Match match : list) {

            int playerCount = paymentService.getPlayerCount(match.getMatch_idx());
            match.setPlayerCount(playerCount);

            String formattedDate = match.getMatch_date().format(formatter);
            match.setFormattedDate(formattedDate);

            String a = match.getMatch_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ' ' + match.getMatch_time();

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime matchDateTime = LocalDateTime.parse(a, formatter1);

            LocalDateTime currentDateTime = LocalDateTime.now();

            LocalDateTime twoHoursBeforeMatch = matchDateTime.minusHours(2);

            boolean isMatchPast = twoHoursBeforeMatch.isBefore(currentDateTime);
            match.setMatchPast(isMatchPast);

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

    @GetMapping("/sales")
    public ModelAndView businessSales(@RequestParam(required = false) String month,
                                      @RequestParam(required = false) String year,
                                      @RequestParam(required = false) String level,
                                      @RequestParam(required = false) String gender,
                                      ModelAndView modelAndView, HttpSession session) {

        Integer idx = (Integer) session.getAttribute("idx");

        List<Match> list = matchService.getMatchListByIdForSales(idx, month, year, gender, level);

        logger.info(">>>>>>>>>>>>>>>> list : " + list.size());

        // match_date별로 그룹화
        Map<LocalDate, List<Match>> groupedByDate = list.stream().collect(Collectors.groupingBy(Match::getMatch_date));

        Map<LocalDate, List<Match>> sortedGroupedByDate = groupedByDate.entrySet()
                .stream()
                .sorted((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey())) // 내림차순 정렬
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1, // 병합 함수
                        LinkedHashMap::new // LinkedHashMap으로 순서 보장
                ));

        Map<LocalDate, Integer> dailyTotalMap = new HashMap<>();

        for (LocalDate date : sortedGroupedByDate.keySet()) {

            List<Match> matchesOnDate = sortedGroupedByDate.get(date);

            for (Match match : matchesOnDate) {
                int playerCount = paymentService.getPlayerCount(match.getMatch_idx());
                match.setPlayerCount(playerCount);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

                String formattedDate = match.getMatch_date().format(formatter);
                match.setFormattedDate(formattedDate);

                String a = match.getMatch_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ' ' + match.getMatch_time();

                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime matchDateTime = LocalDateTime.parse(a, formatter1);

                LocalDateTime currentDateTime = LocalDateTime.now();

                LocalDateTime twoHoursBeforeMatch = matchDateTime.minusHours(2);

                boolean isMatchPast = twoHoursBeforeMatch.isBefore(currentDateTime);
                match.setMatchPast(isMatchPast);
            }

            int dailyTotal = matchesOnDate.stream().mapToInt(match -> match.getPrice() * match.getPlayerCount()).sum();

            dailyTotalMap.put(date, dailyTotal);
        }

        int total = list.stream().filter(Match::isMatchPast).mapToInt(match -> match.getPrice() * match.getPlayerCount()).sum();
        int playerTotal = list.stream().filter(Match::isMatchPast).mapToInt(Match::getPlayerCount).sum();


        modelAndView.setViewName("business/businessSales");
        modelAndView.addObject("groupedByDate", sortedGroupedByDate);
        modelAndView.addObject("list", list.size());
        modelAndView.addObject("dailyTotalMap", dailyTotalMap);
        modelAndView.addObject("total", total);
        modelAndView.addObject("playerTotal", playerTotal);

        return modelAndView;
    }

    @GetMapping("/customer")
    public ModelAndView businessCustomer(ModelAndView modelAndView, HttpSession session) {

        Integer idx = (Integer) session.getAttribute("idx");

        List<Match> list = matchService.getMatchListByIdForSales(idx, null, null, null, null);
        List<Map<String, Object>> results = paymentService.getPaymentListById(idx);

        for (Match match : list) {
            int playerCount = paymentService.getPlayerCount(match.getMatch_idx());
            match.setPlayerCount(playerCount);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

            String formattedDate = match.getMatch_date().format(formatter);
            match.setFormattedDate(formattedDate);

            String a = match.getMatch_date().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ' ' + match.getMatch_time();

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime matchDateTime = LocalDateTime.parse(a, formatter1);

            LocalDateTime currentDateTime = LocalDateTime.now();

            LocalDateTime twoHoursBeforeMatch = matchDateTime.minusHours(2);

            boolean isMatchPast = twoHoursBeforeMatch.isBefore(currentDateTime);
            match.setMatchPast(isMatchPast);
        }

        modelAndView.setViewName("business/businessCustomerList");
        modelAndView.addObject("matchList", list);
        modelAndView.addObject("list", list.size());
        modelAndView.addObject("results", results);

        return modelAndView;
    }
}
