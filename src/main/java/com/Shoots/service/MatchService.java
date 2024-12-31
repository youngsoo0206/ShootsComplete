package com.Shoots.service;

import com.Shoots.domain.Match;

import java.util.List;

public interface MatchService {
    void insertMatch(Match match);
    public List<Match> getMatchList(int page, int limit);

    int getListCount();
}
