package com.Shoots.controller;

import com.Shoots.domain.RegularUser;
import com.Shoots.service.RegularUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/myPage")
public class MyPageController {
    private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);
    private RegularUserService regularUserService;

    public MyPageController(RegularUserService regularUserService) {this.regularUserService = regularUserService;}

    @GetMapping(value="/info")
    public ModelAndView info(ModelAndView mv, String id, HttpServletRequest request) {
        RegularUser user = regularUserService.regularUserList(id);

        if(user != null) {
            logger.info("상세보기 성공");
            mv.setViewName("myPage/info");
            mv.addObject("user", user);
        } else{
            logger.info("상세보기 실패");
        }

        return mv;
    }

    @GetMapping(value="/updateUser")
    public ModelAndView updateUser(ModelAndView mv, String id, HttpServletRequest request) {
        RegularUser user = regularUserService.regularUserList(id);
        if(user != null) {
            logger.info("수정보기 성공");
            mv.setViewName("myPage/updateUser");
            mv.addObject("user", user);
        }

        return mv;
    }

    @PostMapping(value="/updateUserProcess")
    public String updateUserProcess(RegularUser user, HttpServletRequest request, Model model, RedirectAttributes rattr) {
        regularUserService.updateRegularUser(user);
        rattr.addFlashAttribute("result", "updateSuccess");
        return "redirect:/main";
    }
}
