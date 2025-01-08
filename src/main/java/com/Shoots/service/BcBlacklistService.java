package com.Shoots.service;

import com.Shoots.domain.BcBlacklist;

import java.util.List;
import java.util.Map;

public interface BcBlacklistService {
    List<Map<String, Object>> getBlackListById(Integer business_idx);

    void insertBcBlacklist(BcBlacklist bcBlacklist);

    String getStatusById(Object user_idx);
}
