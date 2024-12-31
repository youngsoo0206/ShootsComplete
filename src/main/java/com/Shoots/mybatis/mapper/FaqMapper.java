package com.Shoots.mybatis.mapper;

import com.Shoots.domain.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FaqMapper {
    public List<Faq> getFaqList(); //faqList 가져오기

    public int getListCount(); //faqList 갯수 확인
}
