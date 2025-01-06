package com.Shoots.service;

import com.Shoots.domain.Match;

import java.util.List;

public interface MatchService {
    void insertMatch(Match match);
    public List<Match> getMatchList(String filter, String gender, String level, int page, int limit);

    int getListCount();

    Match getDetail(int matchIdx);

    int updateMatch(Match match);

    int deleteMatch(int matchIdx);

    List<Match> getMatchListById(Integer idx, String filter, String gender, String level, int page, int limit);
}
