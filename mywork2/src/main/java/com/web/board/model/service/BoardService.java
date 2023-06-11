package com.web.board.model.service;

import static com.web.common.JDBCTemplate.close;
import static com.web.common.JDBCTemplate.commit;
import static com.web.common.JDBCTemplate.getConnection;
import static com.web.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.web.board.model.dao.BoardDao;
import com.web.board.model.vo.Board;
import com.web.board.model.vo.BoardComment;
public class BoardService {
	
	private BoardDao dao=new BoardDao();
	
	public int selectBoardCount() {
		Connection conn= getConnection();
		int result=dao.selectBoardCount(conn);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<Board> selectBoard(int cPage, int numPerpage){
		Connection conn=getConnection();
		List<Board> b=dao.selectBoard(conn, cPage,numPerpage);
		close(conn);
		return b;
	}
	
	public Board selectBoardByNo(int boardNo, boolean isRead) {
		Connection conn=getConnection();
		Board b=dao.selectBoardByNo(conn,boardNo);
		if(b!=null&&!isRead) { // isRead == true -> 읽은 상태 / 읽지 않았거나 b가 있으면 실행
			int result=dao.updateBoardReadCount(conn,boardNo);
			if(result>0) {
				commit(conn);
				b.setBoardReadCount(b.getBoardReadCount()+1); // 조회수를 클릭하고 새로고침 해야 반영되는 것을 처리
			}
			else rollback(conn);
		}
		close(conn);
		return b;
	}
	
	public Board selectBoard(int boardNo) {
		Connection conn=getConnection();
		Board b=dao.selectBoardByNo(conn,boardNo);
		int result=dao.updateBoardReadCount(conn,boardNo);
			if(result>0) {
				commit(conn);
			}
			else rollback(conn);
		close(conn);
		return b;
	}
	
	public int insertBoardComment(BoardComment bc) {
		Connection conn=getConnection();
		int result=dao.insertBoardComment(conn,bc);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
	public List<BoardComment> selectBoardComment(int boardNo){
		Connection conn=getConnection();
		List<BoardComment> list=dao.selectBoardComment(conn,boardNo);
		close(conn);
		return list;
	}
	
	public int insertBoardCommentReply(int boardNo, int boardCommentRef) {
		Connection conn=getConnection();
		int result=dao.insertBoardCommentReply(conn, boardNo,boardCommentRef);
		if(result>0) commit(conn);
		else rollback(conn);
		close(conn);
		return result;
	}
	
//	public BoardComment selectBoardCommentReply(int boardNo, int boardCommentRef) {
//		Connection conn=getConnection();
//		BoardComment bc=dao.selectBoardCommentReply(conn, boardNo,boardCommentRef);
//		close(conn);
//		return bc;
//	}
	
	
}
