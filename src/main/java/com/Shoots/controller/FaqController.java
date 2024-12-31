package com.Shoots.controller;

import com.Shoots.domain.Faq;
import com.Shoots.service.FaqService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="/faq")
public class FaqController {
    private final FaqService faqService;

    public FaqController(FaqService faqService) {
        this.faqService = faqService;
    }

    @GetMapping
    public ModelAndView faqList(ModelAndView mv) {
        List<Faq> list = faqService.getFaqList();
        mv.setViewName("faq/faqList");
        mv.addObject("faqList", list);
        return mv;
    }
}
