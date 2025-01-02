package com.Shoots.security;

import com.Shoots.domain.RegularUser;
import com.Shoots.mybatis.mapper.RegularUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    private static Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private RegularUserMapper regularUserMapper;

    public CustomUserDetailsService(RegularUserMapper regularUserMapper) {
        this.regularUserMapper = regularUserMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        logger.info("id는 로그인시 입력한 값 : " + id);

        RegularUser regularUser = regularUserMapper.selectById(id);
        logger.info("로그인 한 유저의 role은 ? :" + regularUser.getRole());
        logger.info("로그인 한 유저의 주민번호는 ? :" + regularUser.getJumin());

        if(regularUser == null) {
            logger.info("id : " + id + "가 없습니다.");

            throw new UsernameNotFoundException("id : " + id + "가 없습니다.");
        }
        return regularUser;
    }

}
