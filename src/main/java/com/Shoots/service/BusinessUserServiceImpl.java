package com.Shoots.service;

import com.Shoots.domain.BusinessUser;
import com.Shoots.mybatis.mapper.BusinessUserMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BusinessUserServiceImpl implements BusinessUserService {

    private BusinessUserMapper businessUserMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public BusinessUserServiceImpl(BusinessUserMapper BusinessUserMapper, @Qualifier("businessUserPasswordEncoder") BCryptPasswordEncoder passwordEncoder) {
        this.businessUserMapper = BusinessUserMapper;
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
        BusinessUser user = businessUserMapper.selectById(id);
        return (user == null) ? -1 : 1;//-1은 아이디가 존재x, 1은 아이디가 존재o
    }

    public BusinessUser selectWithId(String id) {
        BusinessUser user = businessUserMapper.selectById(id);
        return user;
    }


    @Override
    public int selectByIdPassword(String id, String password) {
        BusinessUser user = businessUserMapper.selectById(id);

        if (user == null) {
            return -1; //아이디가 없는 경우
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return 0; //아이디는 있는데 비밀번호 일치X
        }

        return 1;  //아이디와 비밀번호가 일치하는 경우
    }


    @Override
    public int insert(BusinessUser user) {
        return businessUserMapper.insert(user);
    }

}
