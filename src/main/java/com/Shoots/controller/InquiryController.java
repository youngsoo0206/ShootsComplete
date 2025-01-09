package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.Inquiry;
import com.Shoots.domain.PaginationResult;
import com.Shoots.domain.RegularUser;
import com.Shoots.service.InquiryCommentService;
import com.Shoots.service.InquiryService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/inquiry")
public class InquiryController {

    @Value("${my.savefolder}")
    private String saveFolder;

    private static final Logger logger = LoggerFactory.getLogger(InquiryController.class);
    private InquiryService inquiryService;
    private InquiryCommentService inquiryCommentService;

    public InquiryController(InquiryCommentService inquiryCommentService, InquiryService inquiryService) {
        this.inquiryCommentService = inquiryCommentService;
        this.inquiryService = inquiryService;
    }

    @GetMapping(value = "/list")
    public ModelAndView inquiryList(@RequestParam(defaultValue = "1") int page,
                                    ModelAndView mv, HttpSession session) {
        int idx = (int) session.getAttribute("idx");
        String usertype = (String) session.getAttribute("usertype");

        session.setAttribute("referer", "list");
        int limit = 10;

        int listcount = inquiryService.getListCount(usertype, idx);
        List<Inquiry> list = inquiryService.getInquiryList(page, limit, idx, usertype);

        PaginationResult result = new PaginationResult(page, limit, listcount);

        mv.setViewName("inquiry/inquiryList");
        mv.addObject("page", page);
        mv.addObject("maxpage", result.getMaxpage());
        mv.addObject("startpage", result.getStartpage());
        mv.addObject("endpage", result.getEndpage());
        mv.addObject("listcount", listcount);
        mv.addObject("inquiryList", list);
        mv.addObject("limit", limit);
        return mv;

    }


}
