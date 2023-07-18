package com.bs.spring.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.dto.Attachment;
import com.bs.spring.board.model.dto.Board;
@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardAll(SqlSession session, Map<String, Object> page) {
		int cPage=(int) page.get("cPage");
		int numPerpage=(int) page.get("numPerpage");
		RowBounds rb= new RowBounds((cPage-1)*numPerpage, numPerpage);
		
		return session.selectList("board.selectBoardAll", null, rb);
	}

	@Override
	public int insertBoard(SqlSession session, Board b) {
		return session.insert("board.insertBoard",b);
	}

	@Override
	public int countBoardAll(SqlSession session) {
		return session.selectOne("board.countBoardAll");
	}
	
	@Override
	public Board selectBoardByNo(SqlSession session, int boardNo) {
		return session.selectOne("board.selectBoardByNo",boardNo);
	}
	
	@Override
	public int insertAttachment(SqlSession session, Attachment a) {
		return session.insert("board.insertAttachment",a);
	}
}
