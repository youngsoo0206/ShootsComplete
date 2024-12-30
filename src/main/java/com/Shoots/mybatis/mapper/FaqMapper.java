package com.Shoots.mybatis.mapper;

import com.Shoots.domain.Faq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FaqMapper {
    public List<Faq> getFaqList();
}
