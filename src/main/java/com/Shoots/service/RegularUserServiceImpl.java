package com.Shoots.service;

import com.Shoots.domain.RegularUser;
import com.Shoots.mybatis.mapper.RegularUserMapper;
import org.springframework.stereotype.Service;

@Service
public class RegularUserServiceImpl implements RegularUserService {

    private RegularUserMapper regularUserMapper;
    //private final PasswordEncoder passwordEncoder;


    public RegularUserServiceImpl(RegularUserMapper regularUserMapper) {
        this.regularUserMapper = regularUserMapper;
    }

    @Override
    public int selectById(String id) {
        RegularUser user = regularUserMapper.selectById(id);
        return (user == null) ? -1 : 1;//-1은 아이디가 존재x, 1은 아이디가 존재o
    }

    @Override
    public int selectByIdPassword(String id, String password) {
        RegularUser user = regularUserMapper.selectById(id);

        if (user == null) {
            return -1; //아이디가 없는 경우
        }

//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            return 0; //아아디는 존재하지만 비밀번호가 일치하지 않는 경우
//        }

        return 1;  //아이디와 비밀번호가 일치하는 경우 //이거부터 고쳐라
    }


    @Override
    public int insert(RegularUser user) {
        return regularUserMapper.insert(user);
    }

}
