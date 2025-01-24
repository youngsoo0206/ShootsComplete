package com.Shoots.controller;

import com.Shoots.domain.KakaoTokenResponseDto;
import com.Shoots.domain.KakaoUserInfoResponseDto;
import com.Shoots.domain.RegularUser;
import com.Shoots.service.KakaoService;
import com.Shoots.service.RegularUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class SocialLoginController {

    private final KakaoService kakaoService;
    private final RegularUserService regularUserService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/kakaoCallback")
    public ResponseEntity<?> kakoCallback(@RequestParam("code") String code, HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession();

        // 카카오에서 액세스 토큰과 리프레시 토큰을 가져옴
        KakaoTokenResponseDto tokenResponse = kakaoService.getAccessTokenFromKakao(code);
        String accessToken = tokenResponse.getAccessToken();
        String refreshToken = tokenResponse.getRefreshToken();

        // 카카오 액세스 토큰을 세션에 저장 : 로그아웃 할때 토큰을 만료시키기 위함.
        session.setAttribute("kakaoAccessToken", accessToken);

        /* 사용자 정보를 가져오는 코드. 카카오 로그인 유저는 Shoots 앱에 한번이라도 로그인 한 적이 있다면 해당 로컬에서 처음 가입한다 할지라도
        Shoots app에 이미 인증받은 적이 있는 (카카오 인증 ID) 유저이기에 정보 동의 절차를 받지 않음.
        단, 로컬 DB에는 데이터 저장을 한 적이 없는 유저기에 DB에 저장하는 절차를 밟음.
        차후 배포한 뒤엔 원격 서버에서 한번에 DB가 관리 되기 때문에 DB저장 처리가 아닌 바로 기존 사용자 로그인 처리를 함.
         */
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // DB에서 유저를 확인하고, 없다면 신규 회원가입
        RegularUser existingUser = regularUserService.findByKakaoUserId(String.valueOf(userInfo.getId())); // 카카오 ID(고유번호)로 조회
        if (existingUser != null) {// 기존 사용자 로그인

            //로그인 유저에게 스프링 시큐리티 권한을 줘야하는데 우리 프로젝트에서 권한을 줄때 기존 스프링에서 사용 하는 방법 (접두사 ROLE_)을 사용하지 않기 때문에 프로젝트의 권한방법과 맞추기 위한 코드
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(existingUser.getRole()));

            // Spring Security 인증 처리
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(existingUser, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //로그인을 한 사람의 인증정보가 사라지지 않게 세션에 저장. 아래 코드 없으면 인증정보 받아와도 저장이 안돼서 위의 인증처리 코드가 끝난 직후 다시 인증정보가 사라져서 권한이 사라지는 일이 발생.
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            session.setAttribute("idx", existingUser.getIdx());
            session.setAttribute("id", existingUser.getUser_id());
            session.setAttribute("role", existingUser.getRole());
            session.setAttribute("usertype", "A");

            //이 부분 if / else문으로 분기 나눠서 existingUser.getJumin getGender 써가지고 쟤네가 null 이면
            // /Shoots/myPage/info 로 리다이렉트, 둘 다 null 아니면 mainBefore로 리다이렉트.
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location","/Shoots/mainBefore")
                    .build();

        } else { //신규 유저인 경우.
            //자동 회원가입 처리를 위한 정보 삽입
            RegularUser regularUser = new RegularUser();
            regularUser.setUser_id("k_" + userInfo.getId()); //회원 id, 카카오 ID (userinfo.id)는 고유 번호라 중복 걱정X
            regularUser.setName(userInfo.getKakaoAccount().getProfile().getNickName()); //회원이름
            regularUser.setEmail(userInfo.getKakaoAccount().getEmail()); //이메일에 카카오 이메일 삽입

            //난수 5자리를 암호화 후 비밀번호로 설정. 어차피 카카오 로그인은 회원이 있는지만 판별한뒤 자동 로그인이기 때문에 비밀번호 쓸모 x
            Random random = new Random();
            int randomNumber = random.nextInt(100000);
            regularUser.setPassword(passwordEncoder.encode((String.valueOf(randomNumber))));
            regularUserService.insert2(regularUser);

            // 위까지 프로젝트에 DB 처리, 아래부터 if문 (=기존유저 있을때) 에서 했던 로그인 처리 그대로 따옴.

            //로그인 유저에게 스프링 시큐리티 권한을 줘야하는데 우리 프로젝트에서 권한을 줄때 기존 스프링에서 사용 하는 방법 (접두사 ROLE_)을 사용하지 않기 때문에 프로젝트의 권한방법과 맞추기 위한 코드
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(existingUser.getRole()));

            // Spring Security 인증 처리
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(regularUser, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //로그인을 한 사람의 인증정보가 사라지지 않게 세션에 저장. 아래 코드 없으면 인증정보 받아와도 저장이 안돼서 위의 인증처리 코드가 끝난 직후 다시 인증정보가 사라져서 권한이 사라지는 일이 발생.
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            session.setAttribute("idx", regularUser.getIdx());
            session.setAttribute("id", regularUser.getUser_id());
            session.setAttribute("role", regularUser.getRole());
            session.setAttribute("usertype", "A");

            //이 부분 if / else문으로 분기 나눠서 regularUser.getJumin getGender 써가지고 쟤네가 null 이면
            // /Shoots/myPage/info 로 리다이렉트, 둘 다 null 아니면 mainBefore로 리다이렉트.
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location","/Shoots/mainBefore")
                    .build();
        }
    } //kakaoCallback

}
