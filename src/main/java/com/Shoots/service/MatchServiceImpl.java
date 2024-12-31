package com.Shoots.service;

import com.Shoots.domain.Match;
import com.Shoots.mybatis.mapper.MatchMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService{

    private MatchMapper dao;

    public MatchServiceImpl(MatchMapper dao) {
        this.dao = dao;
    }

    @Override
    public void insertMatch(Match match) {

    }

    @Override
    public List<Match> getMatchList(int page, int limit) {

        HashMap<String, Integer> map = new HashMap<String, Integer>();

        int offset = (page - 1) * limit;

        map.put("limit", limit);
        map.put("offset", offset);

        return dao.getMatchList(map);
    }

    @Override
    public int getListCount() {
        return dao.getListCount();
    }
}
