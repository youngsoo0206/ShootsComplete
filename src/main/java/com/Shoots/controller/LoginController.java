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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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
        session.removeAttribute("verifyNumber "); //비밀번호 찾을때 저장했던 인증번호 session을 지움


        if (userPrincipal != null) { // 로그인 상태면 강제로 main으로 보냄
            logger.info("저장된 아이디 : " + userPrincipal.getName());
            mv.setViewName("redirect:/main");
        } else { // 로그인 안된 상태면 로그인 폼 뜸
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
    public String findRegularId() {
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


    @GetMapping(value = "/findRegularPassword")
    public String findRegularPassword() {
        return "home/findRegularPasswordForm";
    }



    @ResponseBody
    @PostMapping("/checkRegularUserWithIdAndEmail")
    public Map<String, Object> checkRegularUserWithIdAndEmail(@RequestBody Map<String, String> params, HttpSession session) {
        session.removeAttribute("verifyNumber");
        String userId = params.get("user_id");
        String email = params.get("email");

        RegularUser user = regularUserService.selectWithIdAndEmail(userId, email);
        Map<String, Object> response = new HashMap<>();


        if (user != null) { //유저 데이터가 있으면 ?
            // 6자리 난수 생성
            String verifyNumber = generateVerificationNumber();

            // 이메일 전송
            MailVO vo = new MailVO();
            vo.setFrom("chldudtn0206@naver.com");
            vo.setTo(email);
            vo.setSubject("Shoots에서 보낸 인증번호 입니다.");
            vo.setText("인증번호는 \" " + verifyNumber + " \" 입니다.");
            logger.info(verifyNumber + " 인증번호 메일 보내기 직전");
            sendMail.sendMail(vo); // 메일 전송
            session.setAttribute("verifyNumber", verifyNumber); //인증번호를 잠시 세션에 저장
            session.setAttribute("promptId", userId); //비밀번호 찾기에서 비밀번호 변경할때 커리문으로 쓰기 위해 user_id를 받아둠
            logger.info(verifyNumber + " 인증번호 메일 보낸 뒤 세션에 저장한 값");
            logger.info("세션에 저장된 promptId: " + session.getAttribute("promptId"));



            response.put("success", true);
            response.put("verifyNumber", verifyNumber); // 클라이언트에 난수를 전달
        } else {
            response.put("success", false);
        }
        return response;
    }

    // 6자리 난수 생성 메소드
    private String generateVerificationNumber() {
        Random random = new Random();
        int number = random.nextInt(1000000); // 0 ~ 999999
        String formattedNumber = String.format("%06d", number); // 항상 6자리로 포맷팅
        return formattedNumber;
    }

    @PostMapping("/verifyNumberProcess")
    public String verifyNumberProcess(@RequestParam String verifyNumber, HttpSession session, HttpServletResponse response) throws IOException {
        String sessionVerifyNumber = (String) session.getAttribute("verifyNumber"); // 세션에서 인증번호 가져오기
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();


        if (sessionVerifyNumber != null && sessionVerifyNumber.equals(verifyNumber)) { //이메일 인증번호와 세션에 저장한 인증번호가 같을 때 (=인증완료)
            session.removeAttribute("verifyNumber");
            out.println("<script type='text/javascript'>");
            out.println("alert('인증되었습니다. 비밀번호 변경 페이지로 이동합니다.')");
            out.println("window.location.href = 'regularUserPasswordForm';");
            out.println("</script>");
            out.flush();
            response.flushBuffer();
        return null;
        } else {
            out.println("<script type='text/javascript'>");
            out.println("alert('인증번호가 일치하지 않습니다. 다시 입력해주세요.')");
            out.println("history.back();");
            out.println("</script>");
            out.flush();
            response.flushBuffer();
        return null;
        }
    }

    @GetMapping(value = "/regularUserPasswordForm")
    String regularUserPasswordForm() {
        return "home/updateRegularUserPasswordForm";
    }

    @PostMapping(value = "/updateRegularUserPasswordProcess")
    public String updateRegularUserPasswordProcess(RegularUser user, Model model,
                                HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        //비밀번호 암호화 추가
        String encPassword = passwordEncoder.encode(user.getPassword());
        logger.info(encPassword);
        user.setPassword(encPassword);

        int result = regularUserService.updateRegularUserPassword(user); //이 시점에서 db에 정보 변경

        if(result==1){ //성공적으로 db에 정보 업데이트 됐을때
            out.println("<script type='text/javascript'>");
            out.println("alert('비밀번호가 성공적으로 변경됐습니다!')");
            out.println("window.location.href = 'login';");
            out.println("</script>");
        }else{
            logger.info("개인회원 비밀번호 업데이트 실패 (updateRegularUserPasswordProcess)");
        }
        return null;
    }

}
