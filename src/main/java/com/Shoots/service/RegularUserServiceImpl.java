package com.Shoots.service;

import com.Shoots.domain.RegularUser;
import com.Shoots.mybatis.mapper.RegularUserMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegularUserServiceImpl implements RegularUserService {

    private RegularUserMapper regularUserMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public RegularUserServiceImpl(RegularUserMapper regularUserMapper, BCryptPasswordEncoder passwordEncoder) {
        this.regularUserMapper = regularUserMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (passwordEncoder == null) {
            System.out.println("passwordEncoder가 주입 안된 상태입니다. : passwordEncoder = null");
        }
    }

    @Override
    public int selectById(String id) {
        RegularUser user = regularUserMapper.selectById(id);
        return (user == null) ? -1 : 1;//-1은 아이디가 존재x, 1은 아이디가 존재o
    }

    public RegularUser selectWithId(String id) {
        RegularUser user = regularUserMapper.selectById(id);
        return user;
    }


    @Override
    public int selectByIdPassword(String id, String password) {
        RegularUser user = regularUserMapper.selectById(id);

        if (user == null) {
            return -1; //아이디가 없는 경우
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return 0; //아이디는 있는데 비밀번호 일치X
        }

        return 1;  //아이디와 비밀번호가 일치하는 경우
    }


    @Override
    public int insert(RegularUser user) {
        return regularUserMapper.insert(user);
    }

    @Override
    public int selectByEmail(String email) {
        RegularUser user = regularUserMapper.selectByEmail(email);
        return (user == null) ? -1 : 1;//-1은 아이디가 존재x, 1은 아이디가 존재o
    }

    @Override
    public RegularUser findIdWithEmail(String email) {
        RegularUser user = regularUserMapper.findIdWithEmail(email);
        return user;
    }
}
