package com.Shoots.controller;

import com.Shoots.domain.MailVO;
import com.Shoots.domain.RegularUser;
import com.Shoots.service.RegularUserService;
import com.Shoots.task.SendMail;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder passwordEncoder;
    private SendMail sendMail;


    public LoginController(RegularUserService regularUserService, BCryptPasswordEncoder passwordEncoder, SendMail sendMail) {
        this.regularUserService = regularUserService;
        this.passwordEncoder = passwordEncoder;
        this.sendMail = sendMail;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(ModelAndView mv, @CookieValue(value = "remember-me", required = false) Cookie readCookie,
                              HttpSession session, Principal userPrincipal) {

        if (readCookie != null) {
            logger.info("저장된 아이디 : " + userPrincipal.getName());
            mv.setViewName("redirect:/main");
        } else {
            mv.setViewName("home/loginForm");
            mv.addObject("loginResult", session.getAttribute("loginResult"));
            session.removeAttribute("loginResult");
        }

        return mv;
    }

    @GetMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }


    @GetMapping(value = "/join")
    public String join() {
        return "home/joinForm";
    }

    @GetMapping("/regularJoinForm")
    public String getRegularJoinForm(Model model) {
        return "fragments/regularJoinForm";
    }

    @PostMapping(value = "/regularJoinProcess")
    public String regularJoinProcess(RegularUser user, RedirectAttributes rattr,
                                      HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        //비밀번호 암호화 추가
        String encPassword = passwordEncoder.encode(user.getPassword());
        logger.info(encPassword);
        user.setPassword(encPassword);

        int result = regularUserService.insert(user);
        PrintWriter out = response.getWriter();

        //삽입 성공하면?
        if (result == 1) {
            out.println("<script type='text/javascript'>");
            out.println("alert('회원가입에 성공했습니다!');");
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


    @ResponseBody
    @GetMapping(value = "/emailcheck")
    public int emailcheck(String email) {
        return regularUserService.selectByEmail(email);
    }

    @GetMapping(value = "/findRegularId")
    public String findId() {
        return "home/findRegularIdForm";
    }

    @PostMapping(value = "/findRegularIdProcess")
    public String findRegularIdProcess(String email, HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        RegularUser user = regularUserService.findIdWithEmail(email);


        if (user == null) {
            out.println("<script type='text/javascript'>");
            out.println("alert('일치하는 이메일이 없습니다. 이메일을 확인해 주세요.')");
            out.println("location.href='/Shoots/findRegularId';");
            out.println("</script>");
            out.flush();
        } else {
            MailVO vo = new MailVO();
            vo.setTo(user.getEmail());
            vo.setSubject("Shoots에서 회원님의 아이디를 전달해 드립니다.");
            vo.setText("회원님의 아이디입니다 : " + user.getUser_id());
            sendMail.sendMail(vo);

            out.println("<script type='text/javascript'>");
            out.println("alert('이메일로 회원님의 아이디를 전달했습니다.')");
            out.println("location.href='/Shoots/login';");
            out.println("</script>");
            out.flush();
        }
        return null;

    }

}
