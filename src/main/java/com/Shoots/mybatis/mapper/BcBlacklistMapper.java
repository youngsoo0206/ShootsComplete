package com.Shoots.mybatis.mapper;

import com.Shoots.domain.BcBlacklist;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BcBlacklistMapper {
    List<Map<String, Object>> getBlackListById(Integer business_idx);

    void insertBcBlacklist(BcBlacklist bcBlacklist);

    String getStatusById(Object user_idx);
}
