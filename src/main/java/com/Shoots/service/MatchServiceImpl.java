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
        dao.insertMatch(match);
    }

    @Override
    public List<Match> getMatchList(String filter, String gender, String level, int page, int limit) {

        HashMap<String, Object> map = new HashMap<String, Object>();

        int offset = (page - 1) * limit;

        map.put("filter", (filter != null && !filter.isEmpty()) ? filter : null);
        map.put("gender", gender);
        map.put("level", level);

        map.put("limit", limit);
        map.put("offset", offset);


        return dao.getMatchList(map);
    }

    @Override
    public int getListCount() {
        return dao.getListCount();
    }

    @Override
    public Match getDetail(int matchIdx) {
        return dao.getDetail(matchIdx);
    }

    @Override
    public int updateMatch(Match match) {
        return dao.updateMatch(match);
    }

    @Override
    public int deleteMatch(int matchIdx) {
        return dao.deleteMatch(matchIdx);
    }

    @Override
    public List<Match> getMatchListById(Integer idx, String filter, String gender, String level, int page, int limit) {

        int offset = (page - 1) * limit;

        HashMap<String, Object> map = new HashMap<>();

        map.put("idx", idx);

        map.put("filter", (filter != null && !filter.isEmpty()) ? filter : null);
        map.put("gender", gender);
        map.put("level", level);

        map.put("limit", limit);
        map.put("offset", offset);


        return dao.getMatchListById(map);
    }

    @Override
    public int getListCountById(Integer idx) {
        return dao.getListCountById(idx);
    }

    @Override
    public List<Match> getMatchListByIdForSales(Integer idx, String month, String year, String gender, String level) {

        HashMap<String, Object> map = new HashMap<>();

        map.put("idx", idx);
        map.put("month", month);
        map.put("year", year);
        map.put("gender", gender);
        map.put("level", level);

        return dao.getMatchListByIdForSales(map);
    }
}
