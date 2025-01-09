package com.Shoots.service;

import com.Shoots.domain.InquiryComment;
import com.Shoots.mybatis.mapper.InquiryCommentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryCommentServiceImpl implements InquiryCommentService{
    private static final Logger logger = LoggerFactory.getLogger(InquiryCommentServiceImpl.class);
    private InquiryCommentMapper inquiryCommentMapper;

    public InquiryCommentServiceImpl(InquiryCommentMapper inquiryCommentMapper) {
        this.inquiryCommentMapper = inquiryCommentMapper;
    }

    @Override
    public int getListCount(int inquiry_id) {
        return inquiryCommentMapper.getListCount(inquiry_id);
    }

    @Override
    public List<InquiryComment> getInquiryCommentList(int page, int limit) {
        return List.of();
    }
}
