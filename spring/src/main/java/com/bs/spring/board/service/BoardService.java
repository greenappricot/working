package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import com.bs.spring.board.model.dto.Board;

public interface BoardService {
	List<Board> selectBoardAll(Map<String, Object> page);
	int insertBoard(Board b);
	int countBoardAll();
	Board selectBoardByNo(int boardNo);
}
