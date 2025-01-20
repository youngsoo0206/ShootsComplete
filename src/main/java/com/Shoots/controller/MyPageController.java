package com.Shoots.controller;

import com.Shoots.domain.Inquiry;
import com.Shoots.domain.Post;
import com.Shoots.domain.RegularUser;
import com.Shoots.service.InquiryService;
import com.Shoots.service.PostService;
import com.Shoots.service.RegularUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value="/myPage")
public class MyPageController {
    private static final Logger logger = LoggerFactory.getLogger(MyPageController.class);
    private final PostService postService;
    private final InquiryService inquiryService;
    private RegularUserService regularUserService;

    public MyPageController(RegularUserService regularUserService, PostService postService, InquiryService inquiryService) {this.regularUserService = regularUserService;
        this.postService = postService;
        this.inquiryService = inquiryService;
    }

    @GetMapping(value="/info")
    public ModelAndView info(ModelAndView mv, HttpServletRequest request, HttpSession session) {
        String id = (String)request.getSession().getAttribute("id");
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

    @ResponseBody
    @GetMapping(value="/validId")
    public int validId(HttpServletRequest request) {
        String id = (String)request.getSession().getAttribute("id");
        return regularUserService.invalidUserId(id);
    }


    @GetMapping(value="/updateUser")
    public ModelAndView updateUser(ModelAndView mv, HttpServletRequest request) {
        String id = (String)request.getSession().getAttribute("id");
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
        return "redirect:info";
    }

    @GetMapping(value="/myPostList")
    public ModelAndView myPostList(ModelAndView mv, HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("idx");
        List<Post> list = postService.getMyPostList(id);
        int count = postService.getMyPostListCount(id);

        mv.setViewName("myPage/myPostList");
        mv.addObject("list", list);
        mv.addObject("count", count);

        return mv;
    }

    @GetMapping(value="/myInquiryList")
    public ModelAndView myInquiryList(ModelAndView mv, HttpServletRequest request) {
        Integer id = (Integer)request.getSession().getAttribute("idx");
        int count = inquiryService.getMyInquiryListCount(id);
        List<Inquiry> list = inquiryService.getMyInquiryList(id);

        for(Inquiry i : list) {
            boolean replyExist = inquiryService.replyComplete(i.getInquiry_idx());
            i.setHasReply(replyExist);
        }

        mv.setViewName("myPage/myInquiryList");
        mv.addObject("list", list);
        mv.addObject("count", count);

        return mv;
    }
}
