package com.Shoots.mybatis.mapper;

import com.Shoots.domain.RegularUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RegularUserMapper {
    public RegularUser selectById(String id);
    public int insert(RegularUser user);
    public RegularUser selectByEmail(String email);
    public RegularUser findIdWithEmail(String email);

    List<Map<String, Object>> getUserListForBusiness(Integer business_idx, String vip, Integer gender, String age);
}
