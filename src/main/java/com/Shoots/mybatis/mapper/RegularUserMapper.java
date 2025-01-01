package com.Shoots.mybatis.mapper;

import com.Shoots.domain.RegularUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegularUserMapper {
    public RegularUser selectById(String id);
    public int insert(RegularUser user);
}
