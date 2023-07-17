package com.bs.spring.board.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.dao.BoardDao;
import com.bs.spring.board.model.dto.Board;
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private SqlSession session;
	@Autowired
	private BoardDao dao;
	
//	@Autowired
//	public BoardServiceImpl(BoardDao dao) {
//		this.dao=dao;
//	}
	
	@Override
	public List<Board> selectBoardAll(Map<String, Object> page) {
		return dao.selectBoardAll(session, page);
	}

	@Override
	public int insertBoard(Board b) {
		return dao.insertBoard(session,b);
	}
	
	@Override
	public int countBoardAll() {
		return dao.countBoardAll(session);
	}
	
	@Override
	public Board selectBoardByNo(int boardNo) {
		return dao.selectBoardByNo(session, boardNo);
	}
	
}
