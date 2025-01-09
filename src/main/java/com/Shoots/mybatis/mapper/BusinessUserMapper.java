package com.Shoots.mybatis.mapper;

import com.Shoots.domain.BusinessUser;
import com.Shoots.domain.RegularUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface BusinessUserMapper {
    public BusinessUser selectById(String id);
    public int insert(BusinessUser user);
    public BusinessUser selectByEmail(String email);
    public BusinessUser findIdWithEmail(String email);
    public BusinessUser selectWithIdAndEmail(HashMap<String, Object> map);
    public int updateBusinessUserPassword(BusinessUser user);

}
