package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.board.model.dto.Board;

public interface BoardDao {
	List<Board> selectBoardAll(SqlSession session, Map<String, Object> page);
	int insertBoard(SqlSession session, Board b);
	int countBoardAll(SqlSession session);
	Board selectBoardByNo(SqlSession session, int boardNo);
}
