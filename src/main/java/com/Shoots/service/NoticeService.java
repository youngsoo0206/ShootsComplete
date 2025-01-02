package com.Shoots.service;

import com.Shoots.domain.Notice;

import java.util.List;

public interface NoticeService {
    public Notice getDetail(int id);
    public List<Notice> getSearchList(String search_word, int page, int limit);
    public int getSearchListCount(String search_word);
    public void setReadCountUpdate(int id);
}
