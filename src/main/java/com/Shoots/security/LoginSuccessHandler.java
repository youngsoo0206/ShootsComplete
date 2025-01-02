package com.Shoots.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private  static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        logger.info("로그인 성공 : LoginSuccessHandler");

        HttpSession session = request.getSession();
        //CustomUserDetailsService 에다가 session으로 뽑아올 정보들을 필드로 등록하고 getter,setter 만든 뒤 여기다 지정



        String url = request.getContextPath()+"/mainBefore";
        response.sendRedirect(url);
    }
}
