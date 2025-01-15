package com.Shoots.service.report;

import com.Shoots.domain.Board;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardMapper dao;

    public BoardServiceImpl(BoardMapper dao) {
        this.dao = dao;
    }

    public void insertBoard(Board board){
        dao.insertBoard(board);
    }

    @Override
    public List<Board> selectBoardList() {
        return dao.selectBoardList();
    }
}
