package com.Shoots.controller;

import com.Shoots.domain.*;
import com.Shoots.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

import static java.util.Locale.filter;


@Controller
@RequestMapping("/business")
@AllArgsConstructor
public class BusinessController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);

    private RegularUserService regularUserService;
    private PaymentService paymentService;
    private MatchService matchService;
    private BcBlacklistService bcBlacklistService;
    private BusinessUserService businessUserService;
    private BusinessInfoService businessInfoService;

    @GetMapping("/dashboardBefore")
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

        logger.info("결제 리스트 사이즈 : " + list.size());

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

    @GetMapping("/MatchParticipants")
    public ModelAndView businessMatchParticipants(ModelAndView modelAndView, HttpSession session) {

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

        modelAndView.setViewName("business/businessMatchParticipants");
        modelAndView.addObject("matchList", list);
        modelAndView.addObject("list", list.size());
        modelAndView.addObject("results", results);

        return modelAndView;
    }

    @GetMapping("/customerList")
    public ModelAndView businessCustomerList(@RequestParam(required = false) String vip,
                                             @RequestParam(required = false) Integer gender,
                                             @RequestParam(required = false) String age,
                                             ModelAndView modelAndView, HttpSession session) {

        Integer business_idx = (Integer) session.getAttribute("idx");

        List<Map<String, Object>> userList = regularUserService.getUserListForBusiness(business_idx, vip, gender, age);

        for (Map<String, Object> user : userList) {

            String jumin = (String) user.get("jumin");

            int year = Integer.parseInt(jumin.substring(0, 2));
            int month = Integer.parseInt(jumin.substring(2, 4));
            int day = Integer.parseInt(jumin.substring(4, 6));

            int fullYear = (year < 22) ? 2000 + year : 1900 + year;

            LocalDate birthDate = LocalDate.of(fullYear, month, day);
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);

            user.put("age", period.getYears());

            String status = bcBlacklistService.getStatusById(user.get("idx"));
            user.put("status", status);
        }

        modelAndView.setViewName("business/businessCustomerList");
        modelAndView.addObject("userList", userList);
        modelAndView.addObject("list", userList.size());
        modelAndView.addObject("business_idx", business_idx);
        return modelAndView;
    }

    @GetMapping("/blacklist")
    public ModelAndView blacklist(@RequestParam(required = false) String block,
                                  @RequestParam(required = false) String unblock,
                                  HttpSession session, ModelAndView modelAndView){

        Integer business_idx = (Integer) session.getAttribute("idx");

        List<Map<String, Object>> blackList = bcBlacklistService.getBlackListById(business_idx, block, unblock);

        blackList.forEach(item -> {
            item.put("unblocked_at", item.getOrDefault("unblocked_at", null));
        });

        modelAndView.setViewName("business/businessBlackList");
        modelAndView.addObject("blackListSize", blackList.size());
        modelAndView.addObject("blackList", blackList);

        return modelAndView;
    }

    @GetMapping("/Settings")
    public ModelAndView settings(HttpSession session, ModelAndView modelAndView){

        Integer business_idx = (Integer) session.getAttribute("idx");

        BusinessUser businessUser = businessUserService.getBusinessUserInfoById(business_idx);
        BusinessInfo businessInfo = businessInfoService.getInfoById(business_idx);

        logger.info("businessUser : " + businessUser.toString());

        logger.info("businessInfo : " + businessInfo);

        modelAndView.setViewName("business/businessSettings");
        modelAndView.addObject("businessUser", businessUser);
        modelAndView.addObject("businessInfo", businessInfo);

        return modelAndView;
    }

    @PostMapping("/addInfo")
    public String BusinessInfoAdd(BusinessInfo businessInfo) {

        businessInfoService.insertBusinessInfo(businessInfo);

        return "redirect:/business/dashboard";
    }

    @PostMapping("/updateInfo")
    public String BusinessInfoUpdate(BusinessInfo businessInfo) {

        businessInfoService.updateBusinessInfo(businessInfo);

        return "redirect:/business/dashboard";
    }
}
