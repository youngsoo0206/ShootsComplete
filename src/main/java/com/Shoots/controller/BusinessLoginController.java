package com.Shoots.controller;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.MailVO;
import com.Shoots.service.BusinessUserService;
import com.Shoots.task.SendMail;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

@Controller
public class BusinessLoginController {

    private static final Logger logger = LoggerFactory.getLogger(BusinessLoginController.class);
    private final BusinessUserService businessUserService;
    private BCryptPasswordEncoder passwordEncoder;
    private SendMail sendMail;



    public BusinessLoginController(BusinessUserService businessUserService, BCryptPasswordEncoder passwordEncoder, SendMail sendMail) {
        this.businessUserService = businessUserService;
        this.passwordEncoder = passwordEncoder;
        this.sendMail = sendMail;
    }

    @GetMapping("/businessJoinForm")
    public String getBusinessJoinForm(Model model) {
        return "fragments/businessJoinForm";
    }

    @PostMapping(value = "/businessJoinProcess")
    public String businessJoinProcess(BusinessUser user, RedirectAttributes rattr,
                                     Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {

        ModelAndView mv = new ModelAndView();
        response.setContentType("text/html;charset=UTF-8");

        //비밀번호 암호화 추가
        String encPassword = passwordEncoder.encode(user.getPassword());
        logger.info(encPassword);
        user.setPassword(encPassword);

        int result = businessUserService.insert(user);
        PrintWriter out = response.getWriter();

        //삽입 성공하면?
        if (result == 1) {
            out.println("<script type='text/javascript'>");
            out.println("alert('기업회원가입에 성공하셨습니다!');");
            out.println("window.location.href='/Shoots/login';");
            out.println("</script>");
            return null;
        } else { //db에  insert 실패하면?
            out.println("<script type='text/javascript'>");
            out.println("alert('기업회원가입에 실패했습니다.');");
            out.println("</script>");
            return null;
        }
    } //BusinessJoinProcess 끝

    @ResponseBody
    @GetMapping(value = "/business_idcheck")
    public int business_idcheck(String id) {
        return businessUserService.selectById(id);
    }

    @ResponseBody
    @GetMapping(value = "/business_emailcheck")
    public int business_emailcheck(String email) {
        return businessUserService.selectByEmail(email);
    }

    @GetMapping(value = "/findBusinessId")
    public String findId() {
        return "home/findBusinessIdForm";
    }


    @PostMapping(value = "/findBusinessIdProcess")
    public String findBusinessIdProcess(String email, HttpServletResponse response) throws IOException {

        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        BusinessUser user = businessUserService.findIdWithEmail(email);


        if (user == null) {
            out.println("<script type='text/javascript'>");
            out.println("alert('일치하는 이메일이 없습니다. 이메일을 확인해 주세요.')");
            out.println("location.href='/Shoots/findRegularId';");
            out.println("</script>");
            out.flush();
        } else {
            MailVO vo = new MailVO();
            vo.setTo(user.getEmail());
            vo.setSubject("Shoots에서 회원님의 기업 아이디를 전달해 드립니다.");
            vo.setText("회원님의 기업 아이디입니다 : " + user.getBusiness_id());
            sendMail.sendMail(vo);

            out.println("<script type='text/javascript'>");
            out.println("alert('이메일로 회원님의 기업 아이디를 전달했습니다.')");
            out.println("location.href='/Shoots/login';");
            out.println("</script>");
            out.flush();
        }
        return null;

    }

}
