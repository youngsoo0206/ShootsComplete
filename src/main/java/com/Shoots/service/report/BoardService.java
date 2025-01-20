package com.Shoots.service.report;

import com.Shoots.domain.Board;

import java.util.List;

public interface BoardService {
    void insertBoard(Board board);
    List<Board> selectBoardList();

    Board selectByBoardNum(int num);

}
