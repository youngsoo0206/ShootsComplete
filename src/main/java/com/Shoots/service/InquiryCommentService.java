package com.Shoots.service;

import com.Shoots.domain.InquiryComment;

import java.util.List;

public interface InquiryCommentService {
    public int getListCount(int inquiry_id);
    public List<InquiryComment> getInquiryCommentList(int page, int limit);
}
