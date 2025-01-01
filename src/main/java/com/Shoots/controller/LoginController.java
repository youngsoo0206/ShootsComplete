package com.Shoots.controller;

import com.Shoots.domain.RegularUser;
import com.Shoots.service.RegularUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final RegularUserService regularUserService;

    public LoginController(RegularUserService regularUserService) {
        this.regularUserService = regularUserService;
    }


    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView mv, @CookieValue(value = "remember-me", required = false) Cookie readCookie,
                              HttpSession session, Principal userPrincipal) {
        if (readCookie != null) {
            logger.info("저장된 아이디 : " + userPrincipal.getName());
            mv.setViewName("redirect:/main");
        } else {
            mv.setViewName("home/loginForm");
            mv.addObject("loginResult", session.getAttribute("loginfail"));
            session.removeAttribute("loginfail");
        }

        return mv;
    }


    @GetMapping(value = "/join")
    public String join() {
        return "home/joinForm";
    }

    @GetMapping("/regularJoinForm")
    public String getRegularJoinForm(Model model) {
        return "fragments/regularJoinForm";
    }

    @GetMapping("/businessJoinForm")
    public String getBusinessJoinForm(Model model) {
        return "fragments/businessJoinForm";
    }

    @PostMapping(value = "/regularJoinProcess")
    public String regularJoinProcess(RegularUser user, RedirectAttributes rattr,
                                     Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView mv = new ModelAndView();
        response.setContentType("text/html;charset=UTF-8");
        //비밀번호 암호화 추가
//        String encPassword = passwordEncoder.encode(user.getPassword());
//        logger.info(encPassword);
//        user.setPassword(encPassword);

        int result = regularUserService.insert(user);
        PrintWriter out = response.getWriter();

        //삽입 성공하면?
        if (result == 1) {
            out.println("<script type='text/javascript'>");
            out.println("alert('회원가입에 성공하였습니다!');");
            out.println("window.location.href='/Shoots/login';");
            out.println("</script>");
            return null;
        } else { //db에  insert 실패하면?
            out.println("<script type='text/javascript'>");
            out.println("alert('회원가입에 실패했습니다.');");
            out.println("</script>");
            return null;
        }
    } //regularJoinProcess 끝

    @ResponseBody
    @GetMapping(value = "/idcheck")
    public int idcheck(String id) {
        return regularUserService.selectById(id);
    }

    @PostMapping(value = "/loginProcess")
    public String loginProcess(String id, String password, RedirectAttributes rattr, HttpSession session,
                               HttpServletResponse response) {
        int result = regularUserService.selectByIdPassword(id, password);

        return null;
    }



    }
