package com.Shoots.service;

import com.Shoots.domain.BusinessUser;

public interface BusinessUserService {
    public int selectById(String id);
    public BusinessUser selectWithId(String id);
    public int selectByIdPassword(String id, String password);
    public int insert(BusinessUser user);
    public int selectByEmail(String email);
    public BusinessUser findIdWithEmail(String email);
    public BusinessUser selectWithIdAndEmail(String business_id, String email);
    public int updateBusinessUserPassword(BusinessUser user);


}
