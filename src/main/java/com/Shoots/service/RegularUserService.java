package com.Shoots.service;

import com.Shoots.domain.RegularUser;

public interface RegularUserService {
    public int selectById(String id);
    public RegularUser selectWithId(String id);
    public int selectByIdPassword(String id, String password);
    public int insert(RegularUser user);

}
