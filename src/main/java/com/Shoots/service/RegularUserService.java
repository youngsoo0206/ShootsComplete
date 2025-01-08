package com.Shoots.service;

import com.Shoots.domain.RegularUser;

import java.util.List;
import java.util.Map;

public interface RegularUserService {
    public int selectById(String id);
    public RegularUser selectWithId(String id);
    public int selectByIdPassword(String id, String password);
    public int insert(RegularUser user);
    public int selectByEmail(String email);
    public RegularUser findIdWithEmail(String email);

    List<Map<String, Object>> getUserListForBusiness(Integer business_idx, String vip, Integer gender, String age);
}
