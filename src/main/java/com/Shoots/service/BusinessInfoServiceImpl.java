package com.Shoots.service;

import com.Shoots.domain.BusinessInfo;
import com.Shoots.mybatis.mapper.BusinessInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class BusinessInfoServiceImpl implements BusinessInfoService{

    private BusinessInfoMapper dao;

    public BusinessInfoServiceImpl(BusinessInfoMapper dao) {
        this.dao = dao;
    }

    @Override
    public BusinessInfo getInfoById(Integer business_idx) {
        return dao.getInfoById(business_idx);
    }
}
