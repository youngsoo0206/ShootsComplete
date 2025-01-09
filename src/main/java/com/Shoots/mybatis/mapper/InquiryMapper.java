package com.Shoots.mybatis.mapper;

import com.Shoots.domain.Inquiry;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface InquiryMapper {
    public int getListCount(String usertype, int idx);
    public List<Inquiry> getInquiryList(HashMap<String,Object> map);

}
