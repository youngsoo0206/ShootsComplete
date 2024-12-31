package com.Shoots.controller;

import com.Shoots.domain.Match;
import com.Shoots.domain.PaginationResult;
import com.Shoots.service.MatchService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/list")
    public ModelAndView matchList(@RequestParam(defaultValue = "1") int page,
                                  ModelAndView modelAndView, HttpSession session) {

        session.setAttribute("refer", "list");
        int limit = 10;
        int listCount = matchService.getListCount();

        List<Match> list = matchService.getMatchList(page, limit);

        PaginationResult result = new PaginationResult(page, limit, listCount);

        modelAndView.setViewName("match/matchList");
        modelAndView.addObject("page", page);
        modelAndView.addObject("maxpage", result.getMaxpage());
        modelAndView.addObject("startpage", result.getStartpage());
        modelAndView.addObject("endpage", result.getEndpage());
        modelAndView.addObject("listcount", listCount);
        modelAndView.addObject("matchList", list);
        modelAndView.addObject("limit", limit);

        return modelAndView;
    }

    @GetMapping("/write")
    public String matchWrite() {
        return "match/matchForm";
    }
}
