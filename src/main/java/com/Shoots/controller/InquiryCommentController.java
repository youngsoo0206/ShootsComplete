package com.Shoots.controller;

import com.Shoots.domain.InquiryComment;
import com.Shoots.service.InquiryCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/inquiryComment")
public class InquiryCommentController {

    private final static Logger logger = LoggerFactory.getLogger(InquiryCommentController.class);
    private InquiryCommentService inquiryCommentService;

    public InquiryCommentController(InquiryCommentService inquiryCommentService) {
        this.inquiryCommentService = inquiryCommentService;
    }


    @PostMapping(value = "/list")
    public ModelAndView CommentList(int inquiry_idx, ModelAndView mv) {
        int listcount = inquiryCommentService.getListCount(inquiry_idx);
        List<InquiryComment> iqList = inquiryCommentService.getInquiryCommentList(inquiry_idx);

        mv.setViewName("inquiry/inquiryDetail");
        mv.addObject("listcount", listcount);
        mv.addObject("iqList", iqList);
        return mv;
    }

}
