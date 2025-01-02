package com.Shoots.service;

import com.Shoots.domain.Notice;
import com.Shoots.mybatis.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper dao;


    public NoticeServiceImpl(NoticeMapper dao) {
        this.dao = dao;
    }

    @Override
    public Notice getDetail(int id) {
        return dao.getDetail(id);
    }

    @Override
    public List<Notice> getSearchList(String search_word, int page, int limit) {
        Map<String, Object> map = new HashMap<String, Object>();
        if(!search_word.isEmpty()){
            map.put("search_word", "%" + search_word + "%");
        }
        int startrow = (page - 1) * limit+1;
        map.put("startrow", startrow);
        int endrow = startrow+limit-1;
        map.put("endrow", endrow);

        return dao.getNoticeList(map);
    }

    @Override
    public int getSearchListCount(String search_word) {
        Map<String, Object> map = new HashMap<>();
        if(!search_word.isEmpty()){
            map.put("search_word", "%" + search_word + "%");
        }
        return dao.getSearchListCount(map);
    }

    @Override
    public void setReadCountUpdate(int id) {
        dao.setReadCountUpdate(id);
    }
}
