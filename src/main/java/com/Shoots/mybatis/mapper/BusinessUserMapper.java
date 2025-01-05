package com.Shoots.mybatis.mapper;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.RegularUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BusinessUserMapper {
    public BusinessUser selectById(String id);
    public int insert(BusinessUser user);
}
