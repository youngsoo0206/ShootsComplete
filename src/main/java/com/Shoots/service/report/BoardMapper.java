package com.Shoots.service.report;

import com.Shoots.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(Board board);
    List<Board> selectBoardList();
    Board selectBoardById(int id);
}
