package com.Shoots.controller;

import com.Shoots.domain.KakaoTokenResponseDto;
import com.Shoots.domain.KakaoUserInfoResponseDto;
import com.Shoots.domain.RegularUser;
import com.Shoots.service.KakaoService;
import com.Shoots.service.RegularUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        // 카카오에서 액세스 토큰과 리프레시 토큰을 가져옴
        KakaoTokenResponseDto tokenResponse = kakaoService.getAccessTokenFromKakao(code);
        String accessToken = tokenResponse.getAccessToken();
        String refreshToken = tokenResponse.getRefreshToken();

        // 사용자 정보를 가져옴
        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // DB에서 유저를 확인하고, 없다면 신규 회원가입
        RegularUser existingUser = regularUserService.findByKakaoUserId(String.valueOf(userInfo.getId())); // 카카오 ID(고유번호)로 조회
        if (existingUser != null) {
            // 기존 사용자 로그인
            return new ResponseEntity<>(HttpStatus.OK);
        } else { //자동 회원가입 처리를 위한 정보 삽입
            RegularUser regularUser = new RegularUser();
            regularUser.setUser_id("Shoots" + userInfo.getId()); //회원 id, 카카오 ID (userinfo.id)는 고유 번호라 중복 걱정X
            regularUser.setName(userInfo.getKakaoAccount().getProfile().getNickName()); //회원이름
            //난수 5자리를 암호화 후 비밀번호로 설정. 어차피 카카오 로그인은 회원이 있는지만 판별한뒤 자동 로그인이기 때문에 비밀번호 쓸모 x
            Random random = new Random();
            int randomNumber = random.nextInt(100000);
            regularUser.setPassword(passwordEncoder.encode((String.valueOf(randomNumber))));
            regularUserService.insert2(regularUser);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

    }

}
