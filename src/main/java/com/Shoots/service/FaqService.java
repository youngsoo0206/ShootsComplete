package com.Shoots.service;

import com.Shoots.domain.Faq;

import java.util.List;

public interface FaqService {
    public List<Faq> getFaqList();

    public int getListCount();
}
