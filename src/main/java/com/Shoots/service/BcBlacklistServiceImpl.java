package com.Shoots.service;

import com.Shoots.domain.BcBlacklist;
import com.Shoots.mybatis.mapper.BcBlacklistMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BcBlacklistServiceImpl implements BcBlacklistService{

    private BcBlacklistMapper dao;

    public BcBlacklistServiceImpl(BcBlacklistMapper dao) {
        this.dao = dao;
    }

    @Override
    public List<Map<String, Object>> getBlackListById(Integer business_idx) {
        return dao.getBlackListById(business_idx);
    }

    @Override
    public void insertBcBlacklist(BcBlacklist bcBlacklist) {
        dao.insertBcBlacklist(bcBlacklist);
    }

    @Override
    public String getStatusById(Object user_idx) {
        return dao.getStatusById(user_idx);
    }
}
