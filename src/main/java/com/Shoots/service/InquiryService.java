package com.Shoots.service;

import com.Shoots.domain.Inquiry;

import java.util.List;

public interface InquiryService {
    public int getListCount(String usertype, int idx);
    public List<Inquiry> getInquiryList(int page, int limit, int idx, String usertype);
}
