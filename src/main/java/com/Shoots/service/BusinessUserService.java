package com.Shoots.service;

import com.Shoots.domain.BusinessUser;

import java.util.List;

public interface BusinessUserService {
    public int selectById(String id);
    public BusinessUser selectWithId(String id);
    public int selectByIdPassword(String id, String password);
    public int insert(BusinessUser user);
    public int selectByEmail(String email);
    public BusinessUser findIdWithEmail(String email);
    public BusinessUser selectWithIdAndEmail(String business_id, String email);
    public int updateBusinessUserPassword(BusinessUser user);
    public List<BusinessUser> getList(String search_word, int page, int limit);
    public int listCount(String search_word);
    public void approveStatus(int id);
    public void refuseStatus(int id);
    public List<BusinessUser> getApprovedList(String search_word, int page, int limit);
    public int listApprovedCount(String search_word);
    public String getEmail(int id);

    BusinessUser getBusinessUserInfoById(Integer business_idx);

    BusinessUser getBusinessUserAddressById(String businessId);
}
